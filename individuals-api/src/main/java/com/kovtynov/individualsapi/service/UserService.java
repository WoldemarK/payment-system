package com.kovtynov.individualsapi.service;

import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import com.kovtynov.individualsapi.client.KeycloakClient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenService tokenService;
    private final KeycloakClient keycloakClient;

    public Mono<TokenResponse> register(UserRegistrationRequest req) {
        // 1. Создать пользователя в Keycloak

        return keycloakClient.createUser(req.getEmail(), req.getPassword())
                // 2. Затем получить токены для нового пользователя
                .then(tokenService.login(req.getEmail(), req.getPassword()));
    }

    public Mono<UserInfoResponse> getCurrentUser(String userId) {
        return keycloakClient.fetchUserInfo(userId);
    }

//    public Mono<TokenDetails>  authenticate(@NotNull @Email String email,
//                                            @NotNull String password) {
//
//    }
}
