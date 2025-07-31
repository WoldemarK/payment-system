package com.kovtunov.individualsapi.validation;

import com.kovtunov.individualsapi.validation.enums.ValidationRegistrationRequestStep;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RegistrationRequestValidation {

    public Mono<Void> validationRequestField(UserRegistrationRequest userRegistrationRequest) {
        log.debug("validationRequestField: {}", userRegistrationRequest);
        return Mono.fromRunnable(() ->
                {
                    for (ValidationRegistrationRequestStep step : ValidationRegistrationRequestStep.values()) {
                        step.validate(userRegistrationRequest);
                    }
                })
                .doOnError(error -> log.error("Validation error: {}", error.getMessage()))
                .onErrorResume(Mono::error)
                .then();
    }
}
