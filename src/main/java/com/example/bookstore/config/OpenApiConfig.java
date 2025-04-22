package com.example.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean
    public OpenAPI bookstoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bookstore API")
                        .description("REST API for managing books in the bookstore application")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Bookstore Team")
                                .email("support@bookstore.com")
                                .url("https://bookstore.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("/").description("Default Server URL")
                ));
    }
}