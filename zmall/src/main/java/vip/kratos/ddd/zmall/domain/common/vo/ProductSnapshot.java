package vip.kratos.ddd.zmall.domain.common.vo;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductSnapshot {
    private Long productId;
    private String name;
    private BigDecimal price;
    private String description;

    public ProductSnapshot(long productId, String name, BigDecimal price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
