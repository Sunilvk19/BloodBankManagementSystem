package com.example.bsm.exception;

public class TransactionNotFoundByIdException extends RuntimeException {

    private  String message;

    public TransactionNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
