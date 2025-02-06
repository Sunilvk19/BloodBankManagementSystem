package com.example.bsm.exception;

public class BloodBankNotFoundByIdException extends RuntimeException {
  private final String message;
    public BloodBankNotFoundByIdException(String message) {
      this.message = message;
    }
    public String getmessage(){
      return message;
    }
}
