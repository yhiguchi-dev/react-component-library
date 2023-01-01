package com.example.springboot.application.service;

import com.example.domain.model.loginsession.LoginSession;
import com.example.domain.model.loginsession.LoginSessionIdentifier;
import com.example.domain.model.loginsession.SessionVerifier;
import com.example.springboot.application.service.loginsession.LoginSessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionVerificationService {

  LoginSessionService loginSessionService;

  public SessionVerificationService(LoginSessionService loginSessionService) {
    this.loginSessionService = loginSessionService;
  }

  public LoginSessionIdentifier verifyAndGet() {
    LoginSession loginSession = loginSessionService.find();
    SessionVerifier verifier = new SessionVerifier(loginSession);
    verifier.verify();
    return loginSession.loginSessionIdentifier();
  }
}
