package com.example.domain.model.user;

import java.util.Objects;

/** ユーザー識別子 */
public class UserIdentifier {
  String value;

  public UserIdentifier(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserIdentifier that = (UserIdentifier) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
