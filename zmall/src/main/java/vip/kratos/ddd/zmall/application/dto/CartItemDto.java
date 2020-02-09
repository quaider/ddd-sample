package vip.kratos.ddd.zmall.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CartItemDto {
    private int quantity;
    private String productName;
    private BigDecimal price;
    private String productDescription;
}
