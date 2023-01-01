package com.example.springboot.application.service;

import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserCreator;
import com.example.springboot.application.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRegistrationService {

  UserService userService;
  PasswordEncodable passwordEncodable;

  public UserRegistrationService(PasswordEncodable passwordEncodable, UserService userService) {
    this.passwordEncodable = passwordEncodable;
    this.userService = userService;
  }

  public void register(String emailAddress, String password) {
    UserCreator creator = new UserCreator(passwordEncodable);
    User user = creator.create(emailAddress, password);
    userService.register(user);
  }
}
