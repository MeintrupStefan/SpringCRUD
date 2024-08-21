package com.meintrup.springcrud.config;

import com.meintrup.springcrud.util.GsonJsonFactory;
import com.meintrup.springcrud.util.JsonFactory;
import io.swagger.v3.core.util.Json;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan( basePackages = {
        "services", "aspects", "api"
})
@EnableAspectJAutoProxy
public class ProjectConfig {
    @Bean
    public JsonFactory jsonFactory() {
        return new GsonJsonFactory();
    }
}
