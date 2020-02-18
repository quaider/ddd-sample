package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.*;
import vip.kratos.ddd.zmall.application.service.OrderApplicationService;
import vip.kratos.ddd.zmall.application.vm.CreateOrderCommand;
import vip.kratos.ddd.zmall.interfaces.vm.OrderVM;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private final OrderApplicationService orderApplicationService;

    public OrderApi(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping("/create/{userId}")
    public OrderVM create(@PathVariable long userId, @Valid @RequestBody CreateOrderCommand command) {
        return orderApplicationService.createOrder(userId, command);
    }

}
