package com.example.servlet.infrastructure.password;

import com.example.domain.model.user.PasswordEncodable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncodable {
  PasswordEncoder passwordEncoder;

  public MyPasswordEncoder() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public String encode(String value) {
    return passwordEncoder.encode(value);
  }

  @Override
  public boolean matches(String rawPassword, String password) {
    return passwordEncoder.matches(rawPassword, password);
  }
}
