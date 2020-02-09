package vip.kratos.ddd.zmall.application.vm;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemModel {
    @Min(value = 1, message = "数量必须大于0")
    private Integer quantity;
    @NotNull(message = "产品不能为空")
    private Long productId;
}
