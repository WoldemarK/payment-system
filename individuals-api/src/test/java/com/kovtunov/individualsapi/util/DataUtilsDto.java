package com.kovtunov.individualsapi.util;

import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;

public class DataUtilsDto {

    private final static String EMAIL = "someEmail@gmail.com";
    private final static String PASSWORD = "somePassword";
    private final static String CONFIRM_PASSWORD = "somePassword";
    private final static String ACCESS_TOKEN = "test-access-token";
    private final static String REFRESH_TOKEN = "test-refresh-token";
    private final static Integer EXPIRES_IN = 3600;
    private final static String TOKEN_TYPE = "Bearer";

    public static UserRegistrationRequest getInstance() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail(EMAIL);
        request.setPassword(PASSWORD);
        request.confirmPassword(CONFIRM_PASSWORD);
        return request;
    }

    public static TokenResponse getToken() {
        TokenResponse response = new TokenResponse();
        response.setAccessToken(ACCESS_TOKEN);
        response.setRefreshToken(REFRESH_TOKEN);
        response.setExpiresIn(EXPIRES_IN);
        response.setTokenType(TOKEN_TYPE);
        return response;
    }
}
