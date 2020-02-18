package vip.kratos.ddd.zmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.kratos.ddd.zmall.infrastructure.repository.jpa.event.jackson.DefaultObjectMapper;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public DefaultObjectMapper objectMapper() {
        return new DefaultObjectMapper();
    }

}
