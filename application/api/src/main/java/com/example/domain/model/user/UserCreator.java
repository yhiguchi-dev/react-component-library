package com.example.domain.model.user;

import java.util.UUID;

/** ユーザー作成者 */
public class UserCreator {

  PasswordEncodable encodable;

  public UserCreator(PasswordEncodable encodable) {
    this.encodable = encodable;
  }

  public User create(String emailAddress, String rawPassword) {
    String password = encodable.encode(rawPassword);
    UserIdentifier userIdentifier = new UserIdentifier(UUID.randomUUID().toString());
    return new User(userIdentifier, new EmailAddress(emailAddress), new Password(password));
  }
}
