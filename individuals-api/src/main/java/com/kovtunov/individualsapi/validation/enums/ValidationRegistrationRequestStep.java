package com.kovtunov.individualsapi.validation.enums;

import com.kovtunov.individualsapi.exception.PasswordException;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import org.springframework.http.HttpStatus;

public enum ValidationRegistrationRequestStep {

    CHECK_NULL_REQUEST {
        @Override
        public void validate(UserRegistrationRequest request) {
            if (request == null) {
                throw new IllegalArgumentException("Request is null");
            }
        }
    },
    CHECK_EMPTY_PASSWORD {
        @Override
        public void validate(UserRegistrationRequest request) {
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Password is empty");
            }
        }
    },
    CHECK_PASSWORD_MATCH {
        @Override
        public void validate(UserRegistrationRequest request) {
            if (request.getPassword() != null && !request.getPassword().equals(request.getConfirmPassword())) {
                throw new IllegalArgumentException("Passwords do not match");
            }
        }
    },
    CHECK_EMPTY_EMAIL {
        @Override
        public void validate(UserRegistrationRequest request) {
            if (request.getEmail() == null || request.getEmail().isEmpty()) {
                throw new PasswordException(
                        "Email is empty",
                        request.getEmail(),
                        "KeycloakUserService",
                        HttpStatus.BAD_REQUEST,
                        "registerUser"
                );
            }
        }
    };

    public abstract void validate(UserRegistrationRequest request);
}
