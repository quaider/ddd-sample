package vip.kratos.ddd.zmall.application.assembler;

import org.springframework.stereotype.Component;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.dto.CartItemDto;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShoppingCartAssembler {

    public CartDto toCartDto(Cart cart) {
        Set<CartItemDto> items = cart.getCartItems().stream().map(this::toCartItemDto).collect(Collectors.toSet());
        BigDecimal totalAmount = items.stream()
                .map(f -> f.getPrice().multiply(BigDecimal.valueOf(f.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return CartDto.builder()
                .items(items)
                .totalAmount(totalAmount)
                .build();
    }

    public CartItemDto toCartItemDto(CartItem item) {
        return CartItemDto.builder()
                .price(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .productDescription(item.getProduct().getDescription())
                .quantity(item.getQuantity())
                .build();
    }
}
