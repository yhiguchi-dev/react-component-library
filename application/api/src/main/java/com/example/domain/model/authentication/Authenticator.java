package com.example.domain.model.authentication;

import com.example.domain.model.authentication.exception.AuthenticationFailureException;
import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;

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
      throw new AuthenticationFailureException("ユーザーが見つかりません");
    }
    String passwordValue = user.password().value();
    if (!passwordEncodable.matches(rawPassword, passwordValue)) {
      throw new AuthenticationFailureException("認証に失敗しました");
    }
  }
}
