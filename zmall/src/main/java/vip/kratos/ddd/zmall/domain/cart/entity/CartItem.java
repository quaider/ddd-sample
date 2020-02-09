package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Getter;
import vip.kratos.ddd.zmall.domain.common.Entity;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;

import java.util.Date;
import java.util.Objects;

@Getter
public class CartItem extends Entity {
    private int quantity;
    private final ProductSnapshot product;
    private Date addTime;

    public CartItem(int quantity, ProductSnapshot product) {
        this(null, quantity, product);
    }

    public CartItem(Long id, int quantity, ProductSnapshot product) {
        Objects.requireNonNull(product);
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.addTime = new Date();
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
        if (this.quantity < 1) this.quantity = 1;
    }
}
