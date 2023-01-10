package com.example.domain.model.authentication.exception;

/** 認証失敗 */
public class AuthenticationFailureException extends RuntimeException {
  public AuthenticationFailureException(String message) {
    super(message);
  }
}
