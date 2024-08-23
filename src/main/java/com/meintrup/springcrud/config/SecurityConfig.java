package com.meintrup.springcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: Enable & configure csrf

        // Use filter chain for every request and enable http
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // TODO: Change clear text pw to secure one and do not commit to repo
        UserDetails admin = User.builder().username("admin").password("password").roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password("password").roles("USER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
