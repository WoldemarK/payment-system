package com.kovtunov.individualsapi.service;

import com.kovtynov.individuals.api.dto.TokenRefreshRequest;
import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<TokenResponse> register(UserRegistrationRequest req);

    Mono<UserInfoResponse> getCurrentUser(String userId);

    Mono<TokenResponse> authRefreshTokenPost(TokenRefreshRequest tokenRefreshRequest);
}
