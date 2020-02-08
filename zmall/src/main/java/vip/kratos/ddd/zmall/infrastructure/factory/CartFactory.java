package vip.kratos.ddd.zmall.infrastructure.factory;

import org.springframework.stereotype.Component;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;
import vip.kratos.ddd.zmall.infrastructure.po.CartItemPO;
import vip.kratos.ddd.zmall.infrastructure.po.CartPO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartFactory {

    public CartItem toCartItem(CartItemPO cartItemPO) {
        ProductSnapshot snapshot = ProductSnapshot.builder()
                .name(cartItemPO.getName())
                .price(cartItemPO.getPrice())
                .description(cartItemPO.getDescription())
                .productId(cartItemPO.getProductId())
                .build();

        return new CartItem(cartItemPO.getQuantity(), snapshot);
    }

    public Cart toCart(CartPO cartPO, List<CartItemPO> cartItemPOList) {
        Set<CartItem> cartItemSet = cartItemPOList.stream().map(this::toCartItem).collect(Collectors.toSet());
        Cart cart = new Cart(cartPO.getUserId(), cartItemSet);
        cart.setId(cartPO.getId());
        cart.setLastChangeTime(cartPO.getLastChangeTime());

        return cart;
    }

    public CartPO toCartPO(Cart cart) {
        return CartPO.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .lastChangeTime(cart.getLastChangeTime())
                .build();
    }

    public CartItemPO toCartItemPO(long cartId, CartItem cartItem) {
        ProductSnapshot snapshot = cartItem.getProduct();
        return CartItemPO.builder()
                .cartId(cartId)
                .quantity(cartItem.getQuantity())
                .productId(snapshot.getProductId())
                .name(snapshot.getName())
                .price(snapshot.getPrice())
                .description(snapshot.getDescription())
                .addTime(cartItem.getAddTime())
                .build();
    }
}
