package com.example.domain.model.loginsession;

import com.example.domain.model.user.UserIdentifier;
import java.time.LocalDateTime;

/** ログインセッション */
public class LoginSession {
  LoginSessionIdentifier loginSessionIdentifier;

  UserIdentifier userIdentifier;
  LoginSessionCreatedAt loginSessionCreatedAt;

  LastLoginAt lastLoginAt;

  int inactiveInterval;

  public LoginSession(
      LoginSessionIdentifier loginSessionIdentifier,
      UserIdentifier userIdentifier,
      LoginSessionCreatedAt loginSessionCreatedAt,
      LastLoginAt lastLoginAt,
      int inactiveInterval) {
    this.loginSessionIdentifier = loginSessionIdentifier;
    this.userIdentifier = userIdentifier;
    this.loginSessionCreatedAt = loginSessionCreatedAt;
    this.lastLoginAt = lastLoginAt;
    this.inactiveInterval = inactiveInterval;
  }

  public LoginSession(UserIdentifier userIdentifier, int inactiveInterval) {
    this(
        new LoginSessionIdentifier(),
        userIdentifier,
        new LoginSessionCreatedAt(),
        new LastLoginAt(),
        inactiveInterval);
  }

  public boolean exists() {
    return loginSessionIdentifier().exists();
  }

  public boolean isExpired() {
    return loginSessionCreatedAt.isAfter(expiredAt());
  }

  LocalDateTime expiredAt() {
    return loginSessionCreatedAt.plusSeconds(inactiveInterval);
  }

  public LoginSessionIdentifier loginSessionIdentifier() {
    return loginSessionIdentifier;
  }

  public UserIdentifier userIdentifier() {
    return userIdentifier;
  }

  public LoginSessionCreatedAt loginSessionCreatedAt() {
    return loginSessionCreatedAt;
  }

  public LastLoginAt lastLoginAt() {
    return lastLoginAt;
  }

  public int inactiveInterval() {
    return inactiveInterval;
  }
}
