package com.example.domain.model.loginsession;

public class LoginSessionExpiredException extends RuntimeException {
  public LoginSessionExpiredException(String message) {
    super(message);
  }
}
