package com.kovtunov.individualsapi.it;

import com.kovtunov.individualsapi.util.DataUtilsDto;
import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class AuthFlowIntegrationTest {

    @Container
    static KeycloakContainer keycloak = new KeycloakContainer
            (
                    "quay.io/keycloak/keycloak:24.0.0"
            )
            .withRealmImportFile("realm-config.json");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("keycloak.url", keycloak::getAuthServerUrl);
    }

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testRegistrationAndLoginFlow() {
        UserRegistrationRequest req = DataUtilsDto.getInstance();
        webTestClient.post().uri("/v1/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TokenResponse.class)
                .value(tokenRes ->
                {
                    assertNotNull(tokenRes.getAccessToken());
                    webTestClient.get().uri("/v1/auth/me")
                            .headers(headers -> headers.setBearerAuth(tokenRes.getAccessToken()))
                            .exchange()
                            .expectStatus().isOk()
                            .expectBody(UserInfoResponse.class)
                            .value(userInfo -> assertEquals("someEmail@gmail.com", userInfo.getEmail()));
                });
    }
}
