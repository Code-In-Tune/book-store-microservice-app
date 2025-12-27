package com.codeintune.bookstore.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Login Request to obtain a JWT via Keycloak Provider")
public class LoginRequest {

    @Schema(description = "Username to log in")
    @NotBlank(message = "{username.notBlank}" )
    private String username;
    @Schema(description = "Password to log in")
    @NotBlank(message = "{password.notBlank}" )
    private String password;
}
