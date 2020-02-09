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
        Cart cart = cartRepository.findCart(userId, false);
        if (cart == null)
            cart = createEmptyCart(userId);
        return cart;
    }

    public Cart findCartWithItems(long userId) {
        Cart cart = cartRepository.findCart(userId, true);
        if (cart == null)
            cart = createEmptyCart(userId);
        return cart;
    }

    public Cart createEmptyCart(long userId) {
        return new Cart(userId);
    }

    public void addCartItem(Cart cart, CartItem item) {
        cart.addCartItem(item, () -> cartRepository.findCartItemByProductId(item.getProduct().getProductId()));
        cartRepository.saveCart(cart);
    }
}
