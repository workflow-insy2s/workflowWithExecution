package com.insy2s.WorkflowService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and() // Enable CORS
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        http.csrf().disable(); // Disable CSRF for simplicity, adjust as needed

        return http.build();
    }
}
