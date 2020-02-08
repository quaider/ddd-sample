package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
            addSameProduct(item);
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
    private void addSameProduct(CartItem newItem) {
        Objects.requireNonNull(newItem);

        CartItem oldItem = cartItems.stream()
                .filter(newItem::equals)
                .findFirst()
                .orElse(null);

        if (oldItem == null) return;

        newItem.increaseQuantity(oldItem.getQuantity());
        cartItems.add(newItem);
    }
}
