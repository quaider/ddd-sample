package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class Cart extends AggregateRoot {
    private long userId;
    private Set<CartItem> cartItems;

    public Cart(long id, long userId) {
        this(id, userId, new HashSet<>());
    }

    public Cart(long id, long userId, Set<CartItem> cartItems) {
        super(id);
        this.userId = userId;
        Objects.requireNonNull(cartItems);
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem item) {
        Objects.requireNonNull(item);
        CartItem oldItem = cartItems.stream()
                .filter(item::equals)
                .findFirst()
                .orElse(null);

        if (oldItem != null) {
            addSameProduct(oldItem, item);
        } else
            cartItems.add(item);
    }

    private void addSameProduct(CartItem oldItem, CartItem newItem) {
        Objects.requireNonNull(oldItem);
        Objects.requireNonNull(newItem);

        if (!oldItem.equals(newItem)) return;

        oldItem.setQuantity(newItem.getQuantity() + 1);
        oldItem.setProduct(newItem.getProduct());
    }
}
