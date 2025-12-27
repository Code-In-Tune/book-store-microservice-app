package com.codeintune.bookstore.configuration.keycloak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class KeycloakWebClientConfiguration {

    @Bean
    public WebClient keycloackWebClient(){
        return WebClient.builder().baseUrl("http://localhost:8080").build();
    }
}
