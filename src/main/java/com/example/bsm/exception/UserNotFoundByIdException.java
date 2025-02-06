package com.example.bsm.exception;

public class UserNotFoundByIdException extends RuntimeException {
    private final String Message;

    public UserNotFoundByIdException(String Message){

        this.Message = Message;
    }
    public String getMessage(){

        return Message;
    }
}
