package vip.kratos.ddd.zmall.domain.cart.repository;

import vip.kratos.ddd.zmall.domain.cart.entity.Cart;

public interface ICartRepository {
    Cart findCart(long userId);
    void saveCart(Cart cart);
}
