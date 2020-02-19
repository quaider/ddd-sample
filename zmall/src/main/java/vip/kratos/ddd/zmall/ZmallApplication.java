package vip.kratos.ddd.zmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

@SpringBootApplication
public class ZmallApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
        SpringApplication.run(ZmallApplication.class, args);
    }
}
