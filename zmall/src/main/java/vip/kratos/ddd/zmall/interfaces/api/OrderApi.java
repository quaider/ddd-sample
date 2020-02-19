package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.*;
import vip.kratos.ddd.zmall.application.service.OrderApplicationService;
import vip.kratos.ddd.zmall.application.vm.CreateCartItemCommand;
import vip.kratos.ddd.zmall.application.vm.CreateOrderCommand;
import vip.kratos.ddd.zmall.interfaces.vm.OrderVM;
import vip.kratos.ddd.zmall.shared.Address;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/create/test")
    public OrderVM createTest() {
        CreateOrderCommand cmd = new CreateOrderCommand();
        cmd.setCouponId(11L);
        cmd.setPayType(1);

        Address address = new Address();
        address.setCountry("中国");
        address.setCity("上海");
        address.setProvince("上海");
        address.setRegion("浦东新区");
        address.setStreet("成山路963弄");
        address.setDetail("detal");
        cmd.setAddress(address);

        CreateCartItemCommand item = new CreateCartItemCommand();
        item.setProductId(1L);
        item.setQuantity(2);

        List<CreateCartItemCommand> items = new ArrayList<>();
        items.add(item);
        cmd.setItemCommand(items);

        return orderApplicationService.createOrder(111, cmd);
    }

}
