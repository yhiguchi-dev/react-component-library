package com.example.springboot.presentation;

import com.example.domain.model.loginsession.LoginSessionExpiredException;
import com.example.domain.model.loginsession.LoginSessionIdentifier;
import com.example.domain.model.loginsession.LoginSessionNotFoundException;
import com.example.springboot.application.service.SessionVerificationService;
import com.example.springboot.presentation.login.LoginRequest;
import com.example.springboot.presentation.registration.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

  SessionVerificationService sessionVerificationService;
  Logger log = LoggerFactory.getLogger(RootController.class);

  public RootController(SessionVerificationService sessionVerificationService) {
    this.sessionVerificationService = sessionVerificationService;
  }

  @GetMapping
  public String get(Model model) {
    try {
      LoginSessionIdentifier loginSessionIdentifier = sessionVerificationService.verifyAndGet();
      return "home";
    } catch (LoginSessionNotFoundException e) {
      log.debug("LoginSessionNotFoundException");
      model.addAttribute("registrationRequest", new RegistrationRequest());
      return "registration";
    } catch (LoginSessionExpiredException e) {
      log.debug("LoginSessionNotFoundException");
      model.addAttribute("loginRequest", new LoginRequest());
      return "login";
    }
  }
}
