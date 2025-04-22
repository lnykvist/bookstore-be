package com.example.bookstore.config;

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
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                        .allowedHeaders("*")  // Allow all headers
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Methods",
                                "Access-Control-Allow-Headers", "Access-Control-Max-Age")
                        .allowCredentials(true)
                        .maxAge(3600);  // Cache preflight request for 1 hour
            }
        };
    }
}