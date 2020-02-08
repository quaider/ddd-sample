package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.cart.entity.valueobject.ProductSnapshot;
import vip.kratos.ddd.zmall.domain.common.Entity;

@Getter
@Setter(AccessLevel.PACKAGE)
public class CartItem extends Entity {
    private int quantity;
    private ProductSnapshot product;

    public CartItem(long id, int quantity, ProductSnapshot product) {
        super(id);
        this.quantity = quantity;
        this.product = product;
    }
}
