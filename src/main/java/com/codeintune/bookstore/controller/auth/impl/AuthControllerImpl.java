package com.codeintune.bookstore.controller.auth.impl;

import com.codeintune.bookstore.controller.auth.AuthController;
import com.codeintune.bookstore.dto.auth.LoginRequest;
import com.codeintune.bookstore.dto.auth.TokenResponse;
import com.codeintune.bookstore.service.auth.AuthServiceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthServiceWrapper authServiceWrapper;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        return authServiceWrapper.login(loginRequest);
    }
}
