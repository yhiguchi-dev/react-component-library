package com.example.domain.model.user;

import java.util.Objects;

/** ユーザー */
public class User {
  UserIdentifier userIdentifier;
  EmailAddress emailAddress;
  Password password;

  public User(UserIdentifier userIdentifier, EmailAddress emailAddress, Password password) {
    this.userIdentifier = userIdentifier;
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public User() {}

  public boolean exists() {
    return Objects.nonNull(userIdentifier);
  }

  public UserIdentifier userIdentifier() {
    return userIdentifier;
  }

  public EmailAddress emailAddress() {
    return emailAddress;
  }

  public Password password() {
    return password;
  }
}
