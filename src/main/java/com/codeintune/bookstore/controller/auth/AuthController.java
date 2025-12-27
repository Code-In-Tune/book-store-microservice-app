package com.codeintune.bookstore.controller.auth;

import com.codeintune.bookstore.dto.auth.LoginRequest;
import com.codeintune.bookstore.dto.auth.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(
        name = "Auth Endpoint",
        description = "Operations related to Authentication"
)
public interface AuthController {

    @Operation(summary = "Login to Keycloak Provider", description = "Login to Keycloak")
    @PostMapping(
            value = "/login",
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    TokenResponse login(@RequestBody @Valid LoginRequest loginRequest);
}
