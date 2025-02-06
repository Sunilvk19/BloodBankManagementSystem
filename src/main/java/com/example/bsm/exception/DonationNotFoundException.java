package com.example.bsm.exception;

public class DonationNotFoundException extends RuntimeException {
  private String message;
    public DonationNotFoundException(String message) {
      this.message = message;
    }

  @Override
  public String getMessage() {
    return message;
  }
}
