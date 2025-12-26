package com.codeintune.bookstore.configuration.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Bookstore API",
                version = "v1",
                description = "API Bookstore"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi bookGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Book Store - Book Endpoint")
                .pathsToMatch("/book/**")
                .build();
    }

    @Bean
    public GroupedOpenApi booksGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Book Store - Books Endpoint")
                .pathsToMatch("/books/**")
                .build();
    }

    @Bean
    public GroupedOpenApi bookSaleGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Book Store - Book Sale Endpoint")
                .pathsToMatch("/sale/**")
                .build();
    }

    @Bean
    public GroupedOpenApi bookSalesGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Book Store - Book Sales Endpoint")
                .pathsToMatch("/sales/**")
                .build();
    }
}
