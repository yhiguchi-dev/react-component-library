package com.example.servlet.application.service;

import com.example.domain.model.authentication.Authenticator;
import com.example.domain.model.user.EmailAddress;
import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;
import com.example.servlet.application.service.user.UserService;

public class PasswordAuthenticationService {

  UserService userService;
  PasswordEncodable passwordEncodable;

  public PasswordAuthenticationService(
      UserService userService, PasswordEncodable passwordEncodable) {
    this.userService = userService;
    this.passwordEncodable = passwordEncodable;
  }

  public User authenticate(String emailAddress, String rawPassword) {
    User user = userService.find(new EmailAddress(emailAddress));
    Authenticator authenticator = new Authenticator(user, passwordEncodable);
    authenticator.authenticate(rawPassword);
    return user;
  }
}
