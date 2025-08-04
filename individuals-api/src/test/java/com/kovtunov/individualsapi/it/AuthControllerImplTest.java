package com.kovtunov.individualsapi.it;

import com.kovtunov.individualsapi.client.KeycloakClient;
import com.kovtunov.individualsapi.service.impl.TokenServiceImpl;
import com.kovtunov.individualsapi.service.impl.UserServiceImpl;
import com.kovtunov.individualsapi.util.DataUtilsDto;
import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebFluxTest(UserServiceImpl.class)
class AuthControllerImplTest {


    @Mock
    KeycloakClient keycloakClient;
    @Mock
    TokenServiceImpl tokenService;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    void registerUserSuccess() {
        when(keycloakClient.createUser
                (
                        DataUtilsDto.getInstance().getEmail(),
                        DataUtilsDto.getInstance().getPassword())
        ).thenReturn(Mono.empty());

        TokenResponse tokens = DataUtilsDto.getToken();

        when(tokenService.login
                (
                        DataUtilsDto.getInstance().getEmail(),
                        DataUtilsDto.getInstance().getPassword())
        ).thenReturn(Mono.just(tokens));

        Mono<TokenResponse> resultMono = userService.register
                (
                        new UserRegistrationRequest
                                (
                                        DataUtilsDto.getInstance().getEmail(),
                                        DataUtilsDto.getInstance().getPassword(),
                                        DataUtilsDto.getInstance().getConfirmPassword()
                                )
                );
        TokenResponse result = resultMono.block();
        assertNotNull(result);
        assertEquals("fakeAccess", result.getAccessToken());

        verify(keycloakClient, times(1)).createUser
                (
                        DataUtilsDto.getInstance().getEmail(),
                        DataUtilsDto.getInstance().getPassword()
                );
        verify(tokenService, times(1)).login
                (
                        DataUtilsDto.getInstance().getEmail(),
                        DataUtilsDto.getInstance().getPassword()
                );
    }
}