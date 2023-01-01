package com.example.domain.model.authentication;

import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;
import com.example.domain.model.user.exception.PasswordUnMatchException;
import com.example.domain.model.user.exception.UserNotFoundException;

/** 認証者 */
public class Authenticator {

  User user;
  PasswordEncodable passwordEncodable;

  public Authenticator(User user, PasswordEncodable passwordEncodable) {
    this.user = user;
    this.passwordEncodable = passwordEncodable;
  }

  public void authenticate(String rawPassword) {
    if (!user.exists()) {
      throw new UserNotFoundException("");
    }
    String passwordValue = user.password().value();
    if (!passwordEncodable.matches(rawPassword, passwordValue)) {
      throw new PasswordUnMatchException("");
    }
  }
}
