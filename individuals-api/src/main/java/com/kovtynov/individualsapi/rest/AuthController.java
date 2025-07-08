package com.kovtynov.individualsapi.rest;

import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import com.kovtynov.individuals.api.dto.UserLoginRequest;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import com.kovtynov.individualsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/me")
    public UserInfoResponse index() {
        return null;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        log.info("login  {}", request);
        return "OK";
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody TokenResponse token) {
        return null;
    }

    @PostMapping("/registration")
    public Mono<ResponseEntity<TokenResponse>> register(@RequestBody UserRegistrationRequest request) {
        return userService.register(request)
                .map(tokenResponse -> ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse));
    }
}
