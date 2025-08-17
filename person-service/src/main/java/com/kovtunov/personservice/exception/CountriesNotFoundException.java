package com.kovtunov.personservice.exception;

public class CountriesNotFoundException extends RuntimeException{
    public CountriesNotFoundException(String message) {
        super(message);
    }
}
