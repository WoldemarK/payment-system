package com.kovtunov.individualsapi.service;

import com.kovtunov.individualsapi.client.KeycloakClient;
import com.kovtunov.individualsapi.service.impl.TokenServiceImpl;
import com.kovtunov.individualsapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private TokenServiceImpl tokenService;
    @Mock
    private KeycloakClient client;
    @InjectMocks
    private UserServiceImpl userService;
}
