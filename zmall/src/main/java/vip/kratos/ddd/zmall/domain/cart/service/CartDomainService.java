package vip.kratos.ddd.zmall.domain.cart.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;

import java.util.Optional;

@Service
public class CartDomainService {

    private final ICartRepository cartRepository;

    public CartDomainService(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<Cart> findCart(long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addCartItem(Cart cart, CartItem item) {

    }
}
