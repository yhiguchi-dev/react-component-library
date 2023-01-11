package com.example.servlet.application.service.user;

import com.example.domain.model.user.*;

public class UserService {
  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void register(User user) {
    userRepository.register(user);
  }

  public User find(EmailAddress emailAddress) {
    return userRepository.find(emailAddress);
  }
}
