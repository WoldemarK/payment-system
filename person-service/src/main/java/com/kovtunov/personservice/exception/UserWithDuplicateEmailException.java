package com.kovtunov.personservice.exception;

public class UserWithDuplicateEmailException extends RuntimeException {
    public UserWithDuplicateEmailException(String message) {
        super(message);
    }
}
