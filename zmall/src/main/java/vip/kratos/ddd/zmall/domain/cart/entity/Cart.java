package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_cart")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends AggregateRoot {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Date lastChangeTime;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "none"), nullable = false, updatable = false)
    @Builder.Default
    @Getter
    private Set<CartItem> items = new HashSet<>();

    public long userId() {
        return userId;
    }

    public Set<CartItem> items() {
        return Collections.unmodifiableSet(items);
    }

    @PrePersist
    public void changed() {
        lastChangeTime = new Date();
    }

    public void addItem(ProductSnapshot snapshot, int quantity) {

        CartItem exist = findExistItem(snapshot.getProductId());

        if (exist == null) {
            items.add(new CartItem(quantity, snapshot));
        } else {
            quantity = exist.getQuantity() + quantity;
            exist.updateQuantity(quantity);
            exist.replaceProduct(snapshot);
        }

        changed();
    }

    public CartItem findExistItem(long productId) {
        return items.stream()
                .filter(f -> f.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}
