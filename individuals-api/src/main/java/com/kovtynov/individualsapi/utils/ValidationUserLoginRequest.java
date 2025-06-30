//package com.kovtynov.individualsapi.utils;
//
//import com.kovtynov.individualsapi.dto.request.UserLoginRequest;
//import com.kovtynov.individualsapi.dto.request.UserRegistrationRequest;
//import com.kovtynov.individualsapi.exception.PasswordException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//public class ValidationUserLoginRequest {
//    public Mono<Void> validationRequestField(UserLoginRequest userLoginRequest) {
//        log.debug("validationRequestField: {}", userLoginRequest);
//        return Mono.fromRunnable(() -> {
//                    if (userLoginRequest == null) {
//                        throw new IllegalArgumentException("Request is null");
//                    }
//                    if (userLoginRequest.password() == null || userLoginRequest.password().isEmpty()) {
//                        throw new IllegalArgumentException("Password is empty");
//                    }
//                    if (userLoginRequest.email() == null || userLoginRequest.email().isEmpty()) {
//                        throw new PasswordException(
//                                "Email is empty",
//                                userLoginRequest.email(),
//                                "KeycloakUserService",
//                                HttpStatus.BAD_REQUEST,
//                                "registerUser"
//                        );
//                    }
//                })
//                .doOnError(error -> log.error("Validation error: {}", error.getMessage()))
//                .onErrorResume(error -> {
//                    if (error instanceof PasswordException) {
//                        return Mono.error(error);
//                    } else {
//                        return Mono.error(new IllegalArgumentException(error.getMessage()));
//                    }
//                }).then();
//    }
//}
