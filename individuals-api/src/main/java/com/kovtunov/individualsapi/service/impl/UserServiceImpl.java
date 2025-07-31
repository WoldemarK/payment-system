package com.kovtunov.individualsapi.service.impl;

import com.kovtunov.individualsapi.client.KeycloakClient;
import com.kovtunov.individualsapi.service.TokenService;
import com.kovtunov.individualsapi.service.UserService;
import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TokenService tokenService;
    private final KeycloakClient keycloakClient;

    /**
     * @param req
     * @return TokenResponse
     * Предназначение метода
     * 1. Создать пользователя в Keycloak
     * 2. Затем получить токены для нового пользователя
     */
    @Override
    public Mono<TokenResponse> register(UserRegistrationRequest req) {
        return keycloakClient.createUser(req.getEmail(), req.getPassword())
                .then(tokenService.login(req.getEmail(), req.getPassword()));
    }

    /**
     * @param userId
     * @return UserInfoResponse
     * Предназначение метода
     * 1. Предоставить текущую информацию о пользователе по его ID
     */
    @Override
    public Mono<UserInfoResponse> getCurrentUser(String userId) {
        return keycloakClient.fetchUserInfo(userId);
    }

}
