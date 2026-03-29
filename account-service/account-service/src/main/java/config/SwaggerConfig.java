package com.banking.accountservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI accountServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account Service API")
                        .description("Banking Microservices - Bank Account Management Service")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Muthu Ayyanar S")
                                .email("muthuayyanarselvaraj@gmail.com")));
    }
}