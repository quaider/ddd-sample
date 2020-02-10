package vip.kratos.ddd.zmall.domain.shared.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
public class ProductSnapshot {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false, length = 100)
    private String name;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;


    @Column(name = "product_description", length = 500)
    private String description;

    public ProductSnapshot(long productId, String name, BigDecimal price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
