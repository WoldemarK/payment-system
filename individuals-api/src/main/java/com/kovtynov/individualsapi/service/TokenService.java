package com.kovtynov.individualsapi.service;

import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individualsapi.client.KeycloakClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final KeycloakClient keycloakClient;

    public Mono<TokenResponse> login(String username, String password) {
        // Вызов к Keycloak за токеном по паролю
        return keycloakClient.requestToken(username, password);
    }

    public Mono<TokenResponse> refresh(String refreshToken) {
        // Вызов к Keycloak за обновлением токена
        return keycloakClient.refreshToken(refreshToken);
    }
}
