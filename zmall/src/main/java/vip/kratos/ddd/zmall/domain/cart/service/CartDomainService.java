package vip.kratos.ddd.zmall.domain.cart.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartItemRepository;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;

import java.util.Optional;

@Service
public class CartDomainService {

    private final ICartRepository cartRepository;
    private final ICartItemRepository cartItemRepository;

    public CartDomainService(ICartRepository cartRepository, ICartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Optional<Cart> findCart(long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addCartItem(Cart cart, CartItem item) {
        cart.update();

        CartItem old = cartItemRepository.findByProductProductId(item.getProduct().getProductId()).orElse(null);
        if (old != null) {
            old.updateQuantity(old.getQuantity() + item.getQuantity());
            cartRepository.save(item.getCart());
            cartItemRepository.save(old);
            return;
        }

        cartRepository.save(item.getCart());
        cartItemRepository.save(item);
    }
}
