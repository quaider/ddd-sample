package vip.kratos.ddd.zmall.domain.cart.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;

@Service
public class CartDomainService {

    private final ICartRepository cartRepository;

    public CartDomainService(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart findCart(long userId) {
        return cartRepository.findCart(userId);
    }

    public Cart create(long userId) {
        Cart cart = new Cart(userId);
        return cart;
    }

    public void addCartItem(Cart cart, CartItem item) {
        cart.addCartItem(item);
        cartRepository.saveCart(cart);
    }
}
