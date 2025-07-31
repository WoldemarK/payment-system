package com.kovtunov.individualsapi.validation.aop;


import com.kovtunov.individualsapi.validation.RegistrationRequestValidation;
import com.kovtunov.individualsapi.validation.interfaces.ValidateRegistrationRequest;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
@RequiredArgsConstructor
public class RegistrationRequestValidationAspect {

    private final RegistrationRequestValidation validation;

    @Around("@annotation(validateRegistrationRequest)")
    public Object validateRequest(ProceedingJoinPoint joinPoint,
                                  ValidateRegistrationRequest validateRegistrationRequest) throws Throwable {

        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof UserRegistrationRequest request) {
            log.debug("Validating registration request for method: {}", joinPoint.getSignature());
            return validation.validationRequestField(request)
                    .doOnSuccess(v -> log.debug("Validation succeeded"))
                    .doOnError(e -> log.error("Validation failed", e))
                    .then(Mono.defer(() -> {
                        try {
                            Object result = joinPoint.proceed();
                            return (Mono<?>) result;
                        } catch (Throwable e) {
                            return Mono.error(e);
                        }
                    }));
        }
        return joinPoint.proceed();
    }
}
