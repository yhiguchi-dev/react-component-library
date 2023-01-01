package com.example.domain.model.user;

/** パスワードエンコード可能 */
public interface PasswordEncodable {
  String encode(String value);

  boolean matches(String rawPassword, String password);
}
