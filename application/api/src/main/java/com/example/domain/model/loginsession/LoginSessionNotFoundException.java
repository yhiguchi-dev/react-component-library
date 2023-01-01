package com.example.domain.model.loginsession;

public class LoginSessionNotFoundException extends RuntimeException {
  public LoginSessionNotFoundException(String message) {
    super(message);
  }
}
