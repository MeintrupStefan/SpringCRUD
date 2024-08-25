package com.meintrup.springcrud.config;

import com.meintrup.springcrud.util.GsonJsonFactory;
import com.meintrup.springcrud.util.JsonFactory;
import io.swagger.v3.core.util.Json;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan( basePackages = {
        "services", "aspects", "api", "security"
})
@EnableAspectJAutoProxy
@EnableFeignClients(
        basePackages = "com.meintrup.springcrud"
)
public class ProjectConfig {
    @Bean
    public JsonFactory jsonFactory() {
        return new GsonJsonFactory();
    }
}
