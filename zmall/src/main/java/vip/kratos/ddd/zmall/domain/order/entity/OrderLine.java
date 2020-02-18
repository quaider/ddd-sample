package vip.kratos.ddd.zmall.domain.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.shared.BaseEntity;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_order_line")
public class OrderLine extends BaseEntity<OrderLine> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Embedded
    private ProductSnapshot product;

    public OrderLine(int quantity, ProductSnapshot product) {
        this.quantity = quantity;
        this.product = product;
    }

    @Override
    public Long identity() {
        return id;
    }
}
