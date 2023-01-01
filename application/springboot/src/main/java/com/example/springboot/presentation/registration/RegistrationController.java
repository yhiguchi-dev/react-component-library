package com.example.springboot.presentation.registration;

import com.example.springboot.application.service.UserRegistrationService;
import com.example.springboot.presentation.login.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

  UserRegistrationService userRegistrationService;

  public RegistrationController(UserRegistrationService userRegistrationService) {
    this.userRegistrationService = userRegistrationService;
  }

  @GetMapping
  public String get(Model model) {
    model.addAttribute("registrationRequest", new LoginRequest());
    return "registration";
  }

  @PostMapping
  public String post(@Validated @ModelAttribute RegistrationRequest registrationRequest) {
    userRegistrationService.register(
        registrationRequest.getEmail(), registrationRequest.getPassword());
    return "login";
  }
}
