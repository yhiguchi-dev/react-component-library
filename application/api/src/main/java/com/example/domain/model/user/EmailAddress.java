package com.example.domain.model.user;

/** メールアドレス */
public class EmailAddress {
  String value;

  public EmailAddress(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
