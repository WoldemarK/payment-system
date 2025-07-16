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
public class AuthController  {

    private final UserService userService;


    @GetMapping("/hello")
    public String hello_message(){
        return "Hello";
    }


    public Mono<ResponseEntity<TokenResponse>> authLoginPost(Mono<UserLoginRequest> userLoginRequest, ServerWebExchange exchange) {
        return null;
    }


    public Mono<ResponseEntity<UserInfoResponse>> authMeGet(ServerWebExchange exchange) {
        return null;
    }


    public Mono<ResponseEntity<TokenResponse>> authRefreshTokenPost(Mono<TokenRefreshRequest> tokenRefreshRequest, ServerWebExchange exchange) {
        return null;
    }


    public Mono<ResponseEntity<TokenResponse>> authRegistrationPost(Mono<UserRegistrationRequest> userRegistrationRequest, ServerWebExchange exchange) {
        return null;
    }
}
