package vip.kratos.ddd.zmall.interfaces.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.kratos.ddd.zmall.application.dto.CartItemDto;
import vip.kratos.ddd.zmall.application.service.CartApplicationService;
import vip.kratos.ddd.zmall.application.vm.CartItemViewModel;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartApi {

    private final CartApplicationService cartService;

    public ShoppingCartApi(CartApplicationService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void add(Long userId, CartItemDto itemDto) {
        cartService.addCartItem(userId, itemDto);
    }

    @GetMapping("/list")
    public List<CartItemViewModel> list() {
        return null;
    }
}
