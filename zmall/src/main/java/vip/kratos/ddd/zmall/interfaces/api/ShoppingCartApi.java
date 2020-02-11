package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.*;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.service.CartApplicationService;
import vip.kratos.ddd.zmall.application.vm.CartItemModel;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class ShoppingCartApi {

    private final CartApplicationService cartService;

    public ShoppingCartApi(CartApplicationService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void add(long userId, @Valid CartItemModel itemModel) {
        cartService.addCartItem(userId, itemModel);
    }

    @PostMapping("/del")
    public void del(long userId, long productId) {
        cartService.removeCartItem(userId, productId);
    }

    @PostMapping("/update")
    public void updateQuantity(long userId, @Valid CartItemModel itemModel) {
        cartService.updateQuantity(userId, itemModel);
    }

    @GetMapping("/list/{userId}")
    public CartDto list(@PathVariable long userId) {
        return cartService.findCart(userId);
    }
}
