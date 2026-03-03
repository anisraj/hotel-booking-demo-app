package me.anisjamadar.hotelbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
            .sessionManagement(c -> {
                c.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(c -> {
                c
                 .requestMatchers("/bookings/**").authenticated()
                 .requestMatchers(HttpMethod.DELETE, "/users").authenticated()
                 .requestMatchers(HttpMethod.PUT, "/users").authenticated()
                 .anyRequest().permitAll();
            });
        return http.build();
    }
}
