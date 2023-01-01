package com.example.springboot.application.service.user;

import com.example.domain.model.user.EmailAddress;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
