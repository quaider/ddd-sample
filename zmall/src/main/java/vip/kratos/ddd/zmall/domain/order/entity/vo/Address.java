package vip.kratos.ddd.zmall.domain.order.entity.vo;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.shared.ValueObject;

import javax.persistence.Column;

@Getter
@Setter
public class Address extends ValueObject<Address> {

    @Column(name = "addr_country", nullable = false)
    private String country;

    @Column(name = "addr_province", nullable = false)
    private String province;

    @Column(name = "addr_city", nullable = false)
    private String city;

    @Column(name = "addr_region", nullable = false)
    private String region;

    @Column(name = "addr_street", nullable = false)
    private String street;

    @Column(name = "addr_detail", nullable = false)
    private String detail;
}
