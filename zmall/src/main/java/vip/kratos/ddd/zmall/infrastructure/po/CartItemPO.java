package vip.kratos.ddd.zmall.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity(name = "t_cart_item")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cartId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long productId;

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
