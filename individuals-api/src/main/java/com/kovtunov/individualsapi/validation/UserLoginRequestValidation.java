package com.kovtunov.individualsapi.validation;

import com.kovtunov.individualsapi.validation.enums.ValidationUserLoginRequestStep;
import com.kovtynov.individuals.api.dto.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UserLoginRequestValidation {

    public Mono<Void> validationRequestField(UserLoginRequest userLoginRequest) {
        log.debug("validationRequestField: {}", userLoginRequest);
        return Mono.fromRunnable(() ->
                {
                    for (ValidationUserLoginRequestStep step : ValidationUserLoginRequestStep.values()) {
                        step.validate(userLoginRequest);
                    }
                })
                .doOnError(error -> log.error("Validation error: {}", error.getMessage()))
                .onErrorResume(Mono::error)
                .then();
    }
}
