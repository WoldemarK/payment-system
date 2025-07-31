package com.kovtunov.individualsapi.client;

import com.kovtunov.individualsapi.dto.UserRepresentationDto;
import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KeycloakClient {

    private final WebClient webClient;

    @Value("${REALM_CONTROLLER.client_id}")
    private String clientId;
    @Value("${controllerUrl.createUserUrl}")
    private String createUserUrl;
    @Value("${REALM_CONTROLLER.clientName}")
    private String clientName;
    @Value("${controllerUrl.requestTokenUrl}")
    private String requestTokenUrl;
    @Value("${controllerUrl.refreshTokenUrl}")
    private String refreshTokenUrl;
    @Value("${controllerUrl.fetchUserInfoUrl}")
    private String fetchUserInfoUrl;

    private final static String PASSWORD = "password";
    private final static String USER_NAME = "username";
    private final static String CLIENT_ID = "client_id";
    private final static String GRAND_TYPE = "grant_type";
    private final static String REFRESH_TOKEN = "refresh_token";

    public KeycloakClient(@Value("${keycloak.url}") String keycloakUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(keycloakUrl)
                .build();
    }

    public Mono<Void> createUser(@NotNull @Email String email,
                                 @NotNull String password) {
        log.info("Method create user : {},{}", email, password);
        return webClient.post()
                .uri(createUserUrl, clientName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new UserRepresentationDto(email, password)))
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<TokenResponse> requestToken(String username, String password) {
        log.info("Method requestToken : {},{}", username, password);
        return webClient.post()
                .uri(requestTokenUrl, clientName)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(GRAND_TYPE, PASSWORD)
                        .with(CLIENT_ID, clientId)
                        .with(USER_NAME, username)
                        .with(PASSWORD, password))
                .retrieve()
                .bodyToMono(TokenResponse.class);

    }

    public Mono<TokenResponse> refreshToken(String refreshToken) {
        log.info("Method refreshToken : {} ", refreshToken);
        return webClient.post()
                .uri(refreshTokenUrl, clientName)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(GRAND_TYPE, REFRESH_TOKEN)
                        .with(CLIENT_ID, clientId)
                        .with(REFRESH_TOKEN, refreshToken))
                .retrieve()
                .bodyToMono(TokenResponse.class);
    }

    public Mono<UserInfoResponse> fetchUserInfo(String userId) {
        log.info("Method fetchUserInfo : {} ", userId);
        return webClient.get()
                .uri(fetchUserInfoUrl, clientId, userId)
                .retrieve()
                .bodyToMono(UserInfoResponse.class);
    }

}
