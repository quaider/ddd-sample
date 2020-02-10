package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vip.kratos.ddd.zmall.domain.common.Entity;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "t_cart_item")
@NoArgsConstructor
@Getter
public class CartItem extends Entity {

    @Column(nullable = false)
    private Integer quantity;

    // 指定这个类是同个数据库表，但是把属性抽取到同一个对象里面去
    @Embedded
    private ProductSnapshot product;

    @Column(nullable = false)
    private Date addTime;

    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "none"), updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;

    public CartItem(Cart cart, int quantity, ProductSnapshot product) {
        this(cart, null, quantity, product);
    }

    public CartItem(Cart cart, Long id, int quantity, ProductSnapshot product) {
        Objects.requireNonNull(product);
        this.id = id;
        this.cart = cart;
        this.quantity = quantity;
        this.product = product;
        this.addTime = new Date();
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    @PrePersist
    @PreUpdate
    protected void makeChange() {
        if (this.addTime == null)
            this.addTime = new Date();
    }
}
