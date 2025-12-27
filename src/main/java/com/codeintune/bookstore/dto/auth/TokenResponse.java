package com.codeintune.bookstore.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "JWT token response")
public class TokenResponse {

    @Schema(description = "Access token (JWT)")
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(description = "Expiration time in seconds")
    @JsonProperty("expires_in")
    private Long expiresIn;

    @Schema(description = "Refresh token")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @Schema(description = "Token type", example = "Bearer")
    @JsonProperty("token_type")
    private String tokenType;

}
