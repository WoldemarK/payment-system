package com.kovtunov.individualsapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PasswordException extends RuntimeException {

    private final Object param;
    private final String className;
    private final HttpStatus status;
    private final String methodName;

    public PasswordException(String message, Object param,
                             String className, HttpStatus status, String methodName) {
        super(message);
        this.param = param;
        this.className = className;
        this.status = status;
        this.methodName = methodName;
    }
}
