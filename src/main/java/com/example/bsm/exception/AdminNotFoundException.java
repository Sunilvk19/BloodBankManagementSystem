package com.example.bsm.exception;

public class AdminNotFoundException extends RuntimeException {

    private final String message;
    public AdminNotFoundException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
