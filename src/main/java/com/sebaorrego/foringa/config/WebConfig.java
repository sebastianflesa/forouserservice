package com.sebaorrego.foringa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                   // aplica a todas las rutas
                        .allowedOrigins("*")                // permite cualquier origen
                        .allowedMethods("*")                // permite todos los métodos (GET, POST, PUT, etc.)
                        .allowedHeaders("*")                // permite cualquier header
                        .allowCredentials(false);           // no se permiten credenciales (cookies, auth headers)
            }
        };
    }
}
