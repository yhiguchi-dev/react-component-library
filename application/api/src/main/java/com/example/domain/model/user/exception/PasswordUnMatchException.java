package com.example.domain.model.user.exception;

/** パスワードが不一致 */
public class PasswordUnMatchException extends RuntimeException {
  public PasswordUnMatchException(String message) {
    super(message);
  }
}
