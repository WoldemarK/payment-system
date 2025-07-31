package com.kovtunov.individualsapi.validation.enums;

import com.kovtynov.individuals.api.dto.UserLoginRequest;
import com.kovtynov.individualsapi.exception.PasswordException;
import org.springframework.http.HttpStatus;

public enum ValidationUserLoginRequestStep {

    CHECK_NULL_REQUEST {
        @Override
        public void validate(UserLoginRequest request) {
            if (request == null) {
                throw new IllegalArgumentException("Request is null");
            }
        }
    },
    CHECK_EMPTY_PASSWORD {
        @Override
        public void validate(UserLoginRequest request) {
            if (request != null && (request.getPassword() == null || request.getPassword().isEmpty())) {
                throw new IllegalArgumentException("Password is empty");
            }
        }
    },
    CHECK_EMPTY_EMAIL {
        @Override
        public void validate(UserLoginRequest request) {
            if (request != null && (request.getEmail() == null || request.getEmail().isEmpty())) {
                throw new PasswordException(
                        "Email is empty",
                        request.getEmail(),
                        "UserLoginRequestValidation", // Имя текущего класса или сервиса
                        HttpStatus.BAD_REQUEST,
                        "validationRequestField" // Имя текущего метода
                );
            }
        }
    };

    public abstract void validate(UserLoginRequest request);
}
