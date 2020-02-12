package vip.kratos.ddd.zmall.domain.order.entity.vo;

import vip.kratos.ddd.zmall.domain.shared.IValueObject;

public enum OrderStatus implements IValueObject<OrderStatus> {
    INIT, PAYING, DISPATCHING, DISPATCHED, RECEIPTED, FINISHED, CLOSED, INVALID;

    @Override
    public boolean sameValueAs(OrderStatus other) {
        return this.equals(other);
    }
}
