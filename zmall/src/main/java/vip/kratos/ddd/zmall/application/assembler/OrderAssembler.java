package vip.kratos.ddd.zmall.application.assembler;

import com.google.common.base.Verify;
import org.springframework.stereotype.Component;
import vip.kratos.ddd.zmall.application.vm.CreateCartItemCommand;
import vip.kratos.ddd.zmall.domain.order.entity.Order;
import vip.kratos.ddd.zmall.domain.order.entity.OrderLine;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;
import vip.kratos.ddd.zmall.interfaces.vm.OrderVM;

import java.util.function.Function;

@Component
public class OrderAssembler {
    public OrderLine toOrderLine(CreateCartItemCommand command, ProductSnapshot snapshot) {
        return new OrderLine(command.getQuantity(), snapshot);
    }

    public OrderLine toOrderLine(CreateCartItemCommand command, Function<Long, ProductSnapshot> expression) {
        Verify.verifyNotNull(expression);
        ProductSnapshot snapshot = expression.apply(command.getProductId());
        return new OrderLine(command.getQuantity(), snapshot);
    }

    public OrderVM toOrderVM(Order order) {
        OrderVM orderVM = new OrderVM();
        orderVM.setOrderId(order.getOrderId());
        orderVM.setOrderSn(order.getOrderSn());
        orderVM.setOrderStatus(order.getStatus().name());
        orderVM.setTotalAmount(order.getTotalAmount());
        orderVM.setUserId(order.getUserId());
        orderVM.setPayAmount(order.getPayAmount());

        return orderVM;
    }
}
