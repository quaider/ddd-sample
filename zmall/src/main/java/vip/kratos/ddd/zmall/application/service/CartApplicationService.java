package vip.kratos.ddd.zmall.application.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.application.assembler.ShoppingCartAssembler;
import vip.kratos.ddd.zmall.application.common.ApplicationException;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.vm.CreateCartItemCommand;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;
import vip.kratos.ddd.zmall.domain.cart.service.CartDomainService;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.repository.IProductRepository;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

import javax.transaction.Transactional;
import java.util.Set;
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
    public void addCartItem(long userId, CreateCartItemCommand cartItemModel) {
        ProductSnapshot product = buildProductFactory(cartItemModel.getProductId()).get();
        Cart cart = findOrCreateIfEmpty(userId);
        cart.addItem(product, cartItemModel.getQuantity());

        cartRepository.save(cart);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCartItem(long userId, Set<Long> productIds) {
        Cart cart = findOrCreateIfEmpty(userId);
        for (long productId: productIds)
            cart.removeItem(productId);

        cartRepository.save(cart);
    }

    @Transactional
    public void removeAll(long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart == null) return;

        cartRepository.delete(cart);
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateQuantity(long userId, CreateCartItemCommand cartItemModel) {
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
                throw ApplicationException.notFound("产品[%s]不存在", productId);
            }
            return ProductSnapshot.fromProduct(product);
        };
    }

}
