package com.example.springboot.application.service.user;

import com.example.domain.model.user.EmailAddress;
import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
