package vip.kratos.ddd.zmall.domain.order.event;

import lombok.Getter;
import vip.kratos.ddd.zmall.domain.order.entity.vo.OrderStatus;
import vip.kratos.ddd.zmall.shared.domain.DomainEvent;

@Getter
public class OrderCreatedEvent extends DomainEvent {

    private String orderSn;
    private OrderStatus orderStatus;

    public OrderCreatedEvent(String orderSn, OrderStatus orderStatus) {
        this.orderSn = orderSn;
        this.orderStatus = orderStatus;
    }
}
