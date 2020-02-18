package vip.kratos.ddd.zmall.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String country;
    private String province;
    private String city;
    private String region;
    private String street;
    private String detail;
}
