package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Verify.verifyNotNull;

@Entity
@Table(name = "t_cart")
@DynamicUpdate
@NoArgsConstructor
public class Cart extends AggregateRoot {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Date lastChangeTime;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "none"))
    private Set<CartItem> items;

    public Cart(long userId, Set<CartItem> items) {
        verifyNotNull(items, "CartItem is required");
        this.userId = userId;
        this.items = items;
    }

    public long userId() {
        return userId;
    }

    public Set<CartItem> items() {
        return items;
    }

    /**
     * 如果对象构建复杂，那么应该使用工厂
     */
    public static final class Builder {
        private final Set<CartItem> cartItems = new HashSet<>();
        private Long userId;

        public Builder(long userId) {
            this.userId = userId;
        }

        public Builder addItem(ProductSnapshot product, int quantity) {
            verifyNotNull(product, "product is required");
            cartItems.add(new CartItem(quantity, product));
            return this;
        }

        public Cart build() {
            return new Cart(userId, cartItems);
        }
    }
}
