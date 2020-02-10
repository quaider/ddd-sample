package vip.kratos.ddd.zmall.domain.cart.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

import java.util.function.Supplier;

@Service
public class CartDomainService {

    private final ICartRepository cartRepository;

    public CartDomainService(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void updateQuantity(Cart cart, long productId, int quantity, Supplier<ProductSnapshot> factory) {
        CartItem cartItem = cart.findExistItem(productId);
        if (cartItem == null) {
            ProductSnapshot product = factory.get();
            cart.addItem(product, quantity);
        } else {
            cartItem.updateQuantity(quantity);
        }
    }
}
