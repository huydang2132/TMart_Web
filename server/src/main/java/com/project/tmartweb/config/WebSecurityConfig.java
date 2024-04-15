package com.project.tmartweb.config;

import com.project.tmartweb.enums.RoleId;
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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers(
                                    apiPrefix + "/users/login",
                                    apiPrefix + "/users/register"
                            )
                            .permitAll()
                            .requestMatchers(GET,
                                    (apiPrefix + "/users/**")).hasRole(RoleId.ADMIN.getRoleName().toUpperCase())
                            .requestMatchers(PUT,
                                    (apiPrefix + "/users/**")).hasRole("ADMIN")
                            .requestMatchers(GET,
                                    (apiPrefix + "/products/**")).hasAnyRole("USER", "ADMIN")
                            .anyRequest().authenticated();
                });
        return http.build();
    }
}
