package vip.kratos.ddd.zmall.application.assembler;

import org.springframework.stereotype.Component;
import vip.kratos.ddd.zmall.application.dto.CartItemDto;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;

@Component
public class ShoppingCartAssembler {
    public CartItem toCartItem(CartItemDto cartItemDto) {
        return null;
    }
}
