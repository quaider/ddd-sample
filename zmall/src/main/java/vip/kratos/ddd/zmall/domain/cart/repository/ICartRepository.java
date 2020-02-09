package vip.kratos.ddd.zmall.domain.cart.repository;

import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;

public interface ICartRepository {
    Cart findCart(long userId, boolean load);

    CartItem findCartItemByProductId(long productId);

    void saveCart(Cart cart);
}
