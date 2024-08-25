package com.meintrup.springcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static String[] SWAGGER_URL_PATHS = new String[] { "/swagger-ui.html", "/swagger-ui/index.html", "/swagger-resources/**",
            "/v3/api-docs/**", "/v3/api-docs**", "/v2/api-docs**", "/webjars/**", "/swaggerfox.js", "/swagger-ui/**" };
    /**
     * The user list is hardcoded here. We can just as well use a database for that & hence be able
     * to create new users.
     */
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "test@account.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE ADMIN"))
            ),
            new User(
                    "test2@account.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE ADMIN"))
            )
    );
    private final Filter jwtAuthFilter;

    public SecurityConfig(@Lazy Filter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Overwrite the defaultSecurityFilterChain of spring
     *
     * @param http security object used to set the filters
     * @return the defined Security Filter chain
     * @throws Exception Exceptions thrown in the chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // csrf can be disabled for non browser access.
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/**/auth/**")
            .permitAll()
            .antMatchers(SWAGGER_URL_PATHS)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // We set our own userDetailsService which is just a lookup of user data
        authenticationProvider.setUserDetailsService(userDetailsService());
        // The password decoder encodes encrypted passwords
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return APPLICATION_USERS.stream()
                                        .filter(u -> u.getUsername()
                                                      .equals(email))
                                        .findFirst()
                                        .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
            }
        };
    }
}
