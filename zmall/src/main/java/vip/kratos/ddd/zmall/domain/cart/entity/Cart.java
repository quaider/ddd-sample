package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

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
        CartItem oldItem = findExistCartItem(item.getProduct().getProductId());

        if (oldItem != null) {
            addSameProduct(item);
        } else
            cartItems.add(item);

        makeChange();
    }

    private CartItem findExistCartItem(long productId) {
        return cartItems.stream()
                .filter(f -> f.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    private void makeChange() {
        this.lastChangeTime = new Date();
    }

    /**
     * 购物车添加相同的商品时，增加商品数量
     */
    private void addSameProduct(CartItem newItem) {
        Objects.requireNonNull(newItem);

        CartItem oldItem = findExistCartItem(newItem.getProduct().getProductId());

        if (oldItem == null) return;
        if (oldItem.getId() != null) newItem.setId(oldItem.getId());

        newItem.increaseQuantity(oldItem.getQuantity());
        cartItems.remove(oldItem);
        cartItems.add(newItem);
    }
}
