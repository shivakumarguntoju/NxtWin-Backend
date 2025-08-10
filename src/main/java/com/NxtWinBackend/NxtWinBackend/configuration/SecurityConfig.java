package com.NxtWinBackend.NxtWinBackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/user/api/register", "/user/api/login","/user/api/**","/event/api/**", "/prediction/api/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
