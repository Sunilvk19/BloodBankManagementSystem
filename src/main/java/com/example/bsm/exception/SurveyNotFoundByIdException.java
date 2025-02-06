package com.example.bsm.exception;

public class SurveyNotFoundByIdException extends RuntimeException {
    private String message;
    public SurveyNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
