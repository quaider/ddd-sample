package vip.kratos.ddd.zmall.domain.order.entity.vo;

import lombok.Data;

@Data
public class Address {
    private String country;
    private String province;
    private String city;
    private String region;
    private String street;
    private String details;
}
