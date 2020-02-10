package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import vip.kratos.ddd.zmall.domain.shared.BaseEntity;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "t_cart_item")
@DynamicUpdate
@NoArgsConstructor
@Getter
public class CartItem extends BaseEntity<CartItem> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    // 指定这个类是同个数据库表，但是把属性抽取到同一个对象里面去
    @Embedded
    private ProductSnapshot product;

    @Column(nullable = false)
    private Date addTime;

    public CartItem(int quantity, ProductSnapshot product) {
        this(null, quantity, product);
    }

    public CartItem(Long id, int quantity, ProductSnapshot product) {
        Objects.requireNonNull(product);
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.addTime = new Date();
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void replaceProduct(ProductSnapshot product) {
        this.product = product;
    }

    @Override
    public Long getIdentity() {
        return id;
    }
}
