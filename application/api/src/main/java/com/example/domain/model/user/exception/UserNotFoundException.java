package com.example.domain.model.user.exception;

/** ユーザーが見つからない */
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
