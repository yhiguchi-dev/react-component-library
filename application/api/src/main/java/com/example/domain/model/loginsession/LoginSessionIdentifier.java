package com.example.domain.model.loginsession;

import java.util.Objects;

/** ログインセッション識別子 */
public class LoginSessionIdentifier {
  String value;

  public LoginSessionIdentifier(String value) {
    this.value = value;
  }

  public LoginSessionIdentifier() {}

  public String value() {
    return value;
  }

  public boolean exists() {
    return Objects.nonNull(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LoginSessionIdentifier that = (LoginSessionIdentifier) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
