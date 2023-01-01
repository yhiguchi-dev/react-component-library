package com.example.servlet.application.service.user;

import com.example.domain.model.user.*;

public class UserService {
  UserRepository userRepository;
  PasswordEncodable passwordEncodable;

  public UserService(UserRepository userRepository, PasswordEncodable passwordEncodable) {
    this.userRepository = userRepository;
    this.passwordEncodable = passwordEncodable;
  }

  public void register(User user) {
    userRepository.register(user);
  }

  public User find(EmailAddress emailAddress) {
    return userRepository.find(emailAddress);
  }
}
