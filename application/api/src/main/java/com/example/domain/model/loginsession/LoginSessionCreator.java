package com.example.domain.model.loginsession;

import com.example.domain.model.user.UserIdentifier;

public class LoginSessionCreator {
  public static LoginSession create(
      String id, String userId, long creationTime, long lastAccessedTime, int maxInactiveInterval) {
    LoginSessionIdentifier loginSessionIdentifier = new LoginSessionIdentifier(id);
    UserIdentifier userIdentifier = new UserIdentifier(userId);
    LoginSessionCreatedAt loginSessionCreatedAt = LoginSessionCreatedAt.ofEpochMilli(creationTime);
    LastLoginAt lastLoginAt = LastLoginAt.ofEpochMilli(lastAccessedTime);
    return new LoginSession(
        loginSessionIdentifier,
        userIdentifier,
        loginSessionCreatedAt,
        lastLoginAt,
        maxInactiveInterval);
  }
}
