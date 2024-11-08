package com.semih.controller;

import com.semih.dto.request.AuthRequest;
import com.semih.dto.request.RefreshTokenRequest;
import com.semih.dto.response.AuthResponse;
import com.semih.dto.response.UserResponse;
import com.semih.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController extends BaseController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path="/register")
    public RootEntity<UserResponse> register(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.register(authRequest));
    }

    @PostMapping(path="/authenticate")
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.authenticate(authRequest));
    }

    @PostMapping(path="/refreshToken")
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
