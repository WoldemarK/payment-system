package com.kovtunov.individualsapi.service.impl;

import com.kovtunov.individualsapi.client.KeycloakClient;
import com.kovtunov.individualsapi.service.TokenService;
import com.kovtynov.individuals.api.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final KeycloakClient keycloakClient;


    /**
     * @param username
     * @param password
     * @return TokenResponse
     * Предназначение метода
     * 1. Вызов к Keycloak за токеном по паролю
     */
    @Override
    public Mono<TokenResponse> login(String username, String password) {
        log.debug("login: {}", username);
        return keycloakClient.requestToken(username, password);
    }

    /**
     * @param refreshToken
     * @return TokenResponse
     * Предназначение метода
     * 1. Вызов к Keycloak за обновлением токена
     */
    @Override
    public Mono<TokenResponse> refresh(String refreshToken) {
        log.debug("refresh: {}", refreshToken);
        return keycloakClient.refreshToken(refreshToken);
    }

    /**
     *
     * @param username
     * @param password
     * @return TokenResponse
     */
    @Override
    public Mono<TokenResponse> requestToken(String username, String password){
        log.debug("Method requestToken : {},{}", username, password);
        return keycloakClient.requestToken(username, password);
    }

}
