package vip.kratos.ddd.zmall.application.vm;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.shared.Address;

import java.util.List;

@Getter
@Setter
public class CreateOrderCommand {

    // 收货地址
    private Address address;

    // 支付方式
    private Integer payType;

    // 优惠券id
    private Long couponId;

    private List<CreateCartItemCommand> itemCommand;
}
