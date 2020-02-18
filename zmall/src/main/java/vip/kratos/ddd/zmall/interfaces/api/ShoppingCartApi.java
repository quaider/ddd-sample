package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.*;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.service.CartApplicationService;
import vip.kratos.ddd.zmall.application.vm.CreateCartItemCommand;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class ShoppingCartApi {

    private final CartApplicationService cartService;

    public ShoppingCartApi(CartApplicationService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void add(long userId, @Valid CreateCartItemCommand command) {
        cartService.addCartItem(userId, command);
    }

    @PostMapping("/del")
    public void del(long userId, String productIds) {
        Set<Long> ids = Arrays.stream(productIds.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toSet());

        cartService.removeCartItem(userId, ids);
    }

    @PostMapping("/update")
    public void updateQuantity(long userId, @Valid CreateCartItemCommand command) {
        cartService.updateQuantity(userId, command);
    }

    @GetMapping("/list/{userId}")
    public CartDto list(@PathVariable long userId) {
        return cartService.findCart(userId);
    }

    @PostMapping("/clear")
    public void clear(long userId) {
        cartService.removeAll(userId);
    }
}
