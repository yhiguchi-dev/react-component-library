package com.example.domain.model.user.exception;

/** ユーザーがすでに存在する */
public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
