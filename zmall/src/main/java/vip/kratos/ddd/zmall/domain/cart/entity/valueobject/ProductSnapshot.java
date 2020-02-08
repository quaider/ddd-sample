package vip.kratos.ddd.zmall.domain.cart.entity.valueobject;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSnapshot {
    private String name;
    private BigDecimal price;
    private String description;
}
