package vip.kratos.ddd.zmall.domain.shared.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vip.kratos.ddd.zmall.domain.shared.ValueObject;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSnapshot extends ValueObject<ProductSnapshot> {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false, length = 100)
    private String name;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;


    @Column(name = "product_description", length = 500)
    private String description;
}
