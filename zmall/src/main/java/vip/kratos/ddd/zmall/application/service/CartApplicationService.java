package vip.kratos.ddd.zmall.application.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.application.assembler.ShoppingCartAssembler;
import vip.kratos.ddd.zmall.application.common.ApplicationException;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.vm.CartItemModel;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;
import vip.kratos.ddd.zmall.domain.cart.service.CartDomainService;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.repository.IProductRepository;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@Service
public class CartApplicationService {

    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;
    private final CartDomainService cartDomainService;
    private final ShoppingCartAssembler assembler;

    public CartApplicationService(ICartRepository cartRepository, IProductRepository productRepository,
                                  CartDomainService cartDomainService, ShoppingCartAssembler assembler) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartDomainService = cartDomainService;
        this.assembler = assembler;
    }

    @Transactional(rollbackOn = Exception.class)
    public void addCartItem(long userId, CartItemModel cartItemModel) {
        ProductSnapshot product = buildProductFactory(cartItemModel.getProductId()).get();
        Cart cart = findOrCreateIfEmpty(userId);
        cart.addItem(product, cartItemModel.getQuantity());

        cartRepository.save(cart);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCartItem(long userId, long productId) {
        Cart cart = findOrCreateIfEmpty(userId);
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateQuantity(long userId, CartItemModel cartItemModel) {
        Cart cart = findOrCreateIfEmpty(userId);
        cartDomainService.updateQuantity(cart, cartItemModel.getProductId(), cartItemModel.getQuantity(),
                buildProductFactory(cartItemModel.getProductId()));

        cartRepository.save(cart);
    }

    public CartDto findCart(long userId) {
        return assembler.toCartDto(findOrCreateIfEmpty(userId));
    }

    private Cart findOrCreateIfEmpty(long userId) {
        return cartRepository.findByUserId(userId).orElse(Cart.builder().userId(userId).build());
    }

    private Supplier<ProductSnapshot> buildProductFactory(long productId) {
        return () -> {
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) {
                throw ApplicationException.notFound("产品不存在：", productId);
            }
            return fromProduct(product);
        };
    }

    private ProductSnapshot fromProduct(Product product) {
        return ProductSnapshot.builder()
                .name(product.getName())
                .productId(product.identity())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

}
