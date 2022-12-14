package com.example.springboot.presentation.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public class RegistrationRequest implements Serializable {
  @NotBlank(message = "メールアドレスを入力してください")
  @Email(message = "不正なメールアドレスの形式です")
  String email;

  @NotBlank(message = "パスワードを入力してください")
  String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
