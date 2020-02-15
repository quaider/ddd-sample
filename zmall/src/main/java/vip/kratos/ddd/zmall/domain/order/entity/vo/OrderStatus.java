package vip.kratos.ddd.zmall.domain.order.entity.vo;

import vip.kratos.ddd.zmall.domain.shared.IValueObject;

public enum OrderStatus implements IValueObject<OrderStatus> {

    /**
     * 新建
     */
    CREATED,

    /**
     * 已支付
     */
    PAID,

    /**
     * 已发货
     */
    DISPATCHED,

    /**
     * 已完成
     */
    FINISHED;

    @Override
    public boolean sameValueAs(OrderStatus other) {
        return this.equals(other);
    }
}
