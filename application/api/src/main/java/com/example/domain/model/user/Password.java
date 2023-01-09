package com.example.domain.model.user;

/** パスワード */
public class Password {
  String value;

  public Password(String value) {
    this.value = value;
  }

  Password() {}

  public String value() {
    return value;
  }
}
