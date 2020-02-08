package vip.kratos.ddd.zmall.domain.cart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.kratos.ddd.zmall.domain.common.AggregateRoot;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cart extends AggregateRoot {
    private long userId;
    private Set<CartItem> cartItems;
}
