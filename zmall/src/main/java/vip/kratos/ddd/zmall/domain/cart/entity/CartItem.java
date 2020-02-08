package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.kratos.ddd.zmall.domain.cart.entity.valueobject.ProductSnapshot;
import vip.kratos.ddd.zmall.domain.common.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
public class CartItem extends Entity {
    private int quantity;
    private ProductSnapshot product;
}
