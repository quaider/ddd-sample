package vip.kratos.ddd.zmall.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.shared.BaseEntity;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

@Getter
@Setter
public class OrderLine extends BaseEntity<OrderLine> {

    protected Long id;

    private int quantity;
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
