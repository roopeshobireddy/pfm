package com.personalfinance.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {
 @Bean OpenAPI personalFinanceOpenAPI() {
  return new OpenAPI().info(new Info().title("Personal Finance Manager API")
   .description("REST API for user and expense management").version("v1"));
 }
}
