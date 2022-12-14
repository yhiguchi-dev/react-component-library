package com.example.springboot.application.service;

import com.example.domain.model.user.EmailAddress;
import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserCreator;
import com.example.domain.model.user.exception.UserAlreadyExistsException;
import com.example.springboot.application.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRegistrationService {

  UserService userService;
  PasswordEncodable passwordEncodable;

  public UserRegistrationService(UserService userService, PasswordEncodable passwordEncodable) {
    this.userService = userService;
    this.passwordEncodable = passwordEncodable;
  }

  public void register(String emailAddress, String password) {
    User registeredUser = userService.find(new EmailAddress(emailAddress));
    throwExceptionIfUserAlreadyExists(registeredUser);
    UserCreator creator = new UserCreator(passwordEncodable);
    User user = creator.create(emailAddress, password);
    userService.register(user);
  }

  void throwExceptionIfUserAlreadyExists(User user) {
    if (user.exists()) {
      throw new UserAlreadyExistsException("ユーザーが既に存在します");
    }
  }
}
