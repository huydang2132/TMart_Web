package com.project.tmartweb.config;

import com.project.tmartweb.fillters.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers(
                                    apiPrefix + "/users/login",
                                    apiPrefix + "/users/register")
                            .permitAll()
                            .requestMatchers(GET, apiPrefix + "/categories/**").permitAll()
                            .requestMatchers(GET, apiPrefix + "/notifications/**").permitAll()
                            .requestMatchers(apiPrefix + "/categories/{id}").permitAll()
                            .requestMatchers(GET, apiPrefix + "/products/**").permitAll()
                            .requestMatchers(GET, apiPrefix + "/products/{id}").permitAll()
                            .requestMatchers(POST, apiPrefix + "/images/**").permitAll()
                            .requestMatchers(GET, apiPrefix + "/products/category/{id}").permitAll()
                            .requestMatchers(GET, apiPrefix + "/token/{token}").hasAnyRole("USER", "ADMIN")
                            .requestMatchers(POST, apiPrefix + "/orders/**").hasRole("USER")
                            .requestMatchers(apiPrefix + "/carts/**").hasRole("USER")
                            .requestMatchers(apiPrefix + "/users/**").hasRole("ADMIN")
                            .requestMatchers(apiPrefix + "/products/**").hasRole("ADMIN")
                            .requestMatchers(PUT, apiPrefix + "/orders/**").hasRole("ADMIN")
                            .requestMatchers(apiPrefix + "/categories/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
