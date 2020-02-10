package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_cart")
@DynamicUpdate
@NoArgsConstructor
@Getter
public class Cart extends AggregateRoot {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Date lastChangeTime;

    public Cart(long userId) {
        this.userId = userId;
    }

    public CartItem createItem(int quantity, ProductSnapshot product) {
        return new CartItem(this, quantity, product);
    }

    public void update() {
        this.lastChangeTime = new Date();
    }

    public static Cart createEmptyCart(long userId) {
        return new Cart(userId);
    }
}
