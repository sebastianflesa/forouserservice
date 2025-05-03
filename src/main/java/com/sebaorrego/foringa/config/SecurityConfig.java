package com.sebaorrego.foringa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())       // habilita CORS sin restricciones
            .csrf(csrf -> csrf.disable())          // desactiva CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll())         // permite todas las rutas
            .httpBasic().disable()                 // desactiva auth básica
            .formLogin().disable();                // desactiva formulario login
        return http.build();
    }
}