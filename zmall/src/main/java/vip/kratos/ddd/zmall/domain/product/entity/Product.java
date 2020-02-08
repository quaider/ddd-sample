package vip.kratos.ddd.zmall.domain.product.entity;

import lombok.Getter;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

import java.math.BigDecimal;

@Getter
public class Product extends AggregateRoot {
    private String name;
    private String description;
    private BigDecimal price;

    public Product(Long id, String name, String description, BigDecimal price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
