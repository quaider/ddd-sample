package vip.kratos.ddd.zmall.domain.order.entity.vo;

import lombok.Getter;
import vip.kratos.ddd.zmall.domain.shared.ValueObject;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

@Getter
public class Address extends ValueObject<Address> {
    private String country;
    private String province;
    private String city;
    private String region;
    private String street;
    private String details;
}
