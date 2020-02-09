package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

@Getter
public class Cart extends AggregateRoot {
    private long userId;
    private Set<CartItem> cartItems;
    @Setter
    private Date lastChangeTime;

    public Cart(long userId) {
        this(userId, new HashSet<>());
    }

    public Cart(long userId, Set<CartItem> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems == null ? new HashSet<>() : cartItems;
    }

    public void addCartItem(CartItem item, Supplier<CartItem> oldCartItemSupplier) {
        Objects.requireNonNull(item);
        CartItem oldItem = oldCartItemSupplier.get();

        if (oldItem != null) {
            addSameProduct(item, oldItem);
        } else
            cartItems.add(item);

        makeChange();
    }

    private void makeChange() {
        this.lastChangeTime = new Date();
    }

    /**
     * 购物车添加相同的商品时，增加商品数量
     */
    private void addSameProduct(CartItem newItem, CartItem oldItem) {
        Objects.requireNonNull(newItem);
        if (oldItem == null) return;
        if (oldItem.getId() != null) newItem.setId(oldItem.getId());

        newItem.increaseQuantity(oldItem.getQuantity());
        cartItems.remove(oldItem);
        cartItems.add(newItem);
    }
}
