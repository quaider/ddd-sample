package vip.kratos.ddd.zmall.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity(name = "t_cart_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private long cartId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;

    @Column(nullable = false)
    private Date addTime;

    @PrePersist
    protected void prePersist() {
        if (addTime == null)
            addTime = new Date();
    }
}
