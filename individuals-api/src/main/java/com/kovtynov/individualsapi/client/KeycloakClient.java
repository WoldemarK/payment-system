package com.kovtynov.individualsapi.client;

import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class KeycloakClient {

    private final WebClient webClient;
    private static final String REALM_NAME = "individuals-api-client";
    private static final String CLIENT_ID = "individuals-api-client";

    public KeycloakClient(@Value("${keycloak.url}") String keycloakUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(keycloakUrl)
                .build();
    }

    public Mono<Object> createUser(@NotNull @Email String email, @NotNull String password) {
        return null;
    }

    public Mono<TokenResponse> requestToken(String username, String password) {
        return webClient.post()
                .uri("/realms/individuals-api-realm/protocol/openid-connect/token", REALM_NAME)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "password")
                        .with("client_id", CLIENT_ID)
                        .with("username", username)
                        .with("password", password))
                .retrieve()
                .bodyToMono(TokenResponse.class);

   }

    public Mono<TokenResponse> refreshToken(String refreshToken) {
        return null;
    }

    public Mono<UserInfoResponse> fetchUserInfo(String userId) {
        return null;
    }


}
