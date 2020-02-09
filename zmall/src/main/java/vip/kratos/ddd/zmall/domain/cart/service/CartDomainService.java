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
        return findCartInternal(userId, false);
    }

    public Cart findCartWithItems(long userId) {
        return findCartInternal(userId, true);
    }

    public void addCartItem(Cart cart, CartItem item) {
        cart.addCartItem(item, () -> cartRepository.findCartItemByProductId(item.getProduct().getProductId()));
        cartRepository.saveCart(cart);
    }

    public void updateCartItemQuantity(Cart cart, long productId, int quantity) {
        cart.updateQuantity(quantity, () -> cartRepository.findCartItemByProductId(productId));
        cartRepository.saveCart(cart);
    }

    private Cart findCartInternal(long userId, boolean loadCartItems) {
        Cart cart = cartRepository.findCart(userId, loadCartItems);
        if (cart == null)
            cart = Cart.createEmptyCart(userId);
        return cart;
    }
}
