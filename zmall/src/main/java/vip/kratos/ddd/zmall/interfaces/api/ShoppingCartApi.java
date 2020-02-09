package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.*;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.service.CartApplicationService;
import vip.kratos.ddd.zmall.application.vm.CartItemModel;

@RestController
@RequestMapping("/cart")
public class ShoppingCartApi {

    private final CartApplicationService cartService;

    public ShoppingCartApi(CartApplicationService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void add(long userId, CartItemModel itemModel) {
        cartService.addCartItem(userId, itemModel);
    }

    @PostMapping("/update")
    public void updateQuantity(long userId, CartItemModel itemModel) {
        cartService.updateQuantity(userId, itemModel);
    }

    @GetMapping("/list/{userId}")
    public CartDto list(@PathVariable Long userId) {
        return cartService.findCart(userId);
    }
}
