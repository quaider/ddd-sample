package vip.kratos.ddd.zmall.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.common.Entity;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;

@Getter
@Setter
public class OrderLine extends Entity {
    private int quantity;
    private ProductSnapshot product;

    public OrderLine(int quantity, ProductSnapshot product) {
        this.quantity = quantity;
        this.product = product;
    }
}
