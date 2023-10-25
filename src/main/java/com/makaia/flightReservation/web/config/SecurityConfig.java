package com.makaia.flightReservation.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .cors().and()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.GET, "/v1/**").hasAnyRole("ADMIN","CUSTOMER")
                    .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();

        return http.build();
    }
}
