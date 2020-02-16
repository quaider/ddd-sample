package vip.kratos.ddd.zmall.interfaces.vm;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVM {
    private Long orderId;
    private String orderSn;
    private Long userId;
    private BigDecimal totalAmount;
    private String orderStatus;
}
