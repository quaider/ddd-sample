package vip.kratos.ddd.zmall.domain.order.event;

import lombok.Getter;
import vip.kratos.ddd.zmall.domain.order.entity.vo.OrderStatus;
import vip.kratos.ddd.zmall.domain.shared.DomainEvent;

@Getter
public class OrderCreatedEvent extends DomainEvent<OrderCreatedEvent> {

    private String orderSn;
    private OrderStatus orderStatus;

    public OrderCreatedEvent(String orderSn, OrderStatus orderStatus) {
        this.orderSn = orderSn;
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean sameEventAs(OrderCreatedEvent other) {
        return other != null && orderSn.equals(other.orderSn) && orderStatus.sameValueAs(other.orderStatus);
    }
}
