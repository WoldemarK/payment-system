package com.kovtynov.individualsapi.rest;

import com.kovtynov.individuals.api.client.AuthApi;
import com.kovtynov.individuals.api.dto.*;
import com.kovtynov.individualsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController  implements  AuthApi {

    private final UserService userService;


    @GetMapping("/hello")
    public String hello_message() {
        return "Hello";
    }


    @Override
    public ResponseEntity<TokenResponse> authLoginPost(UserLoginRequest userLoginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<UserInfoResponse> authMeGet() {
        return null;
    }

    @Override
    public ResponseEntity<TokenResponse> authRefreshTokenPost(TokenRefreshRequest tokenRefreshRequest) {
        return null;
    }

    @Override
    public ResponseEntity<TokenResponse> authRegistrationPost(UserRegistrationRequest userRegistrationRequest) {
        return null;
    }
}
