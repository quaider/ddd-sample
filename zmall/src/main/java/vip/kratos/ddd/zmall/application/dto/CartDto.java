package vip.kratos.ddd.zmall.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
public class CartDto {
    private Set<CartItemDto> items;
    private BigDecimal totalAmount;
}
