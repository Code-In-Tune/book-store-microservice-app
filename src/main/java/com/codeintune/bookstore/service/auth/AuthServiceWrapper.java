package com.codeintune.bookstore.service.auth;

import com.codeintune.bookstore.dto.auth.LoginRequest;
import com.codeintune.bookstore.dto.auth.TokenResponse;

public interface AuthServiceWrapper {

    TokenResponse login(LoginRequest loginRequest);
}
