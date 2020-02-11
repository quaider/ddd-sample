package vip.kratos.ddd.zmall.domain.product.entity;

import lombok.*;
import vip.kratos.ddd.zmall.domain.shared.AggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product extends AggregateRoot<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Override
    public Long identity() {
        return productId;
    }
}
