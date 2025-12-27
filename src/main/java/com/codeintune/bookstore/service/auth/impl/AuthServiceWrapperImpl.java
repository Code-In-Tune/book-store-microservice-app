package com.codeintune.bookstore.service.auth.impl;

import com.codeintune.bookstore.dto.auth.LoginRequest;
import com.codeintune.bookstore.dto.auth.TokenResponse;
import com.codeintune.bookstore.service.auth.AuthServiceWrapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthServiceWrapperImpl implements AuthServiceWrapper {

    private final WebClient webClient;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        MultiValueMap<@NonNull String, @NonNull String> formData = new LinkedMultiValueMap<>();
        formData.add("username", loginRequest.getUsername());
        formData.add("password", loginRequest.getPassword());
        formData.add("grant_type", "password");
        formData.add("client_id", "spring-app");

        return webClient.post()
                .uri("/realms/book-store-realm/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();
    }
}
