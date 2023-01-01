package com.example.springboot.application.service.loginsession;

import com.example.domain.model.loginsession.LoginSession;
import com.example.domain.model.loginsession.LoginSessionRepository;
import com.example.domain.model.user.UserIdentifier;
import org.springframework.stereotype.Service;

@Service
public class LoginSessionService {

  LoginSessionRepository loginSessionRepository;

  public LoginSessionService(LoginSessionRepository loginSessionRepository) {
    this.loginSessionRepository = loginSessionRepository;
  }

  public void register(UserIdentifier userIdentifier, int inactiveInterval) {
    LoginSession loginSession = new LoginSession(userIdentifier, inactiveInterval);
    loginSessionRepository.register(loginSession);
  }

  public LoginSession find() {
    return loginSessionRepository.find();
  }
}
