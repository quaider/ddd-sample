package vip.kratos.ddd.zmall.application.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import vip.kratos.ddd.zmall.domain.order.event.OrderCreatedEvent;

@Component
public class OrderEventHandler {

    /**
     * 监听不同事件，Spring 会根据事件对象去调用相应的方法
     * 如果事件跟当前业务无太大关系(比如统计什么的） 这个方法可以设置 @Async
     */
    @TransactionalEventListener
    public void orderCreated(OrderCreatedEvent orderCreated) {
        System.out.println("订单被创建, 单号: " + orderCreated.getOrderSn());
    }

}
