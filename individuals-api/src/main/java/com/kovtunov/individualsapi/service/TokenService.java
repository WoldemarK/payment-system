package com.kovtunov.individualsapi.service;

import com.kovtynov.individuals.api.dto.TokenResponse;
import reactor.core.publisher.Mono;

public interface TokenService {

    Mono<TokenResponse> login(String username, String password);

    Mono<TokenResponse> refresh(String refreshToken);

    Mono<TokenResponse> requestToken(String username, String password);
}
