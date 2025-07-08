package com.kovtynov.individualsapi.utils;

import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import com.kovtynov.individualsapi.exception.PasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ValidationRegistrationRequestRequest {

    public Mono<Void> validationRequestField(UserRegistrationRequest userRegistrationRequest) {
        log.debug("validationRequestField: {}", userRegistrationRequest);
        return Mono.fromRunnable(() -> {
                    if (userRegistrationRequest == null) {
                        throw new IllegalArgumentException("Request is null");
                    }
                    if (userRegistrationRequest.getPassword() == null || userRegistrationRequest.getPassword().isEmpty()) {
                        throw new IllegalArgumentException("Password is empty");
                    }
                    if (!userRegistrationRequest.getPassword().equals(userRegistrationRequest.getConfirmPassword())) {
                        throw new IllegalArgumentException("Passwords do not match");
                    }
                    if (userRegistrationRequest.getEmail() == null || userRegistrationRequest.getEmail().isEmpty()) {
                        throw new PasswordException(
                                "Email is empty",
                                userRegistrationRequest.getEmail(),
                                "KeycloakUserService",
                                HttpStatus.BAD_REQUEST,
                                "registerUser"
                        );
                    }
                })
                .doOnError(error -> log.error("Validation error: {}", error.getMessage()))
                .onErrorResume(error -> {
                    if (error instanceof PasswordException) {
                        return Mono.error(error);
                    } else {
                        return Mono.error(new IllegalArgumentException(error.getMessage()));
                    }
                }).then();
    }
}
