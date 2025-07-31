package com.kovtunov.individualsapi.util;

import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;

public class DataUtilsDto {

    public static UserRegistrationRequest getInstance() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("someEmail@gmail.com");
        request.setPassword("somePassword");
        request.confirmPassword("somePassword");
        return request;
    }

    public static TokenResponse getToken() {
        TokenResponse response = new TokenResponse();
        response.setAccessToken("test-access-token");
        response.setRefreshToken("test-refresh-token");
        response.setExpiresIn(3600);
        response.setTokenType("Bearer");
        return response;
    }
}
