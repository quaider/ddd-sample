package vip.kratos.ddd.zmall.application.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.application.common.ApplicationException;
import vip.kratos.ddd.zmall.application.dto.CartItemDto;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.service.CartDomainService;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.service.ProductDomainService;

import javax.transaction.Transactional;

@Service
public class CartApplicationService {

    private final CartDomainService cartDomainService;
    private final ProductDomainService productDomainService;

    public CartApplicationService(CartDomainService cartService, ProductDomainService productDomainService) {
        this.cartDomainService = cartService;
        this.productDomainService = productDomainService;
    }

    @Transactional(rollbackOn = Exception.class)
    public void addCartItem(long userId, CartItemDto cartItemDto) {
        Cart cart = cartDomainService.findCart(userId);
        Product product = productDomainService.findProduct(cartItemDto.getProductId());
        if (product == null) {
            throw ApplicationException.notFound("对应产品不存在：" + cartItemDto.getProductId());
        }

        ProductSnapshot snapshot = fromProduct(product);
        CartItem cartItem = new CartItem(cartItemDto.getQuantity(), snapshot);
        cartDomainService.addCartItem(cart, cartItem);
    }

    private ProductSnapshot fromProduct(Product product) {
        return ProductSnapshot.builder()
                .productId(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

}
