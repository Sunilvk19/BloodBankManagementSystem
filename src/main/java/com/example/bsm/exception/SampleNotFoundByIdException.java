package com.example.bsm.exception;

public class SampleNotFoundByIdException extends RuntimeException {
    private final String message;
    public SampleNotFoundByIdException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
