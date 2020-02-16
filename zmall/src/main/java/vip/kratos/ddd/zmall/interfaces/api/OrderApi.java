package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.kratos.ddd.zmall.application.vm.CreateOrderCommand;
import vip.kratos.ddd.zmall.interfaces.vm.OrderVM;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @PostMapping("/create")
    public OrderVM create(@Valid CreateOrderCommand command) {

        return null;
    }

}
