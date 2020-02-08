package vip.kratos.ddd.zmall.domain.product.entity;

import lombok.Getter;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;
import vip.kratos.ddd.zmall.infrastructure.po.ProductPO;

import java.math.BigDecimal;

@Getter
public class Product extends AggregateRoot {
    private String name;
    private String description;
    private BigDecimal price;

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product fromProductPO(ProductPO po) {
        Product product = new Product(po.getName(), po.getDescription(), po.getPrice());
        product.setId(po.getId());
        return product;
    }
}
