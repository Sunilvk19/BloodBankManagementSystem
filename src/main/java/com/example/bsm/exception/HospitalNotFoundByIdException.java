package com.example.bsm.exception;

public class HospitalNotFoundByIdException extends RuntimeException {
    private final String message;
    public HospitalNotFoundByIdException(String meassage)
    {
        this.message=meassage;
    }
    public String getMessage(){
        return message;
    }
}
