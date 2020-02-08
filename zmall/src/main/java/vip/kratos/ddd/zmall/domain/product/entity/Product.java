package vip.kratos.ddd.zmall.domain.product.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends AggregateRoot {
    private String name;
    private String description;
    private BigDecimal price;
}
