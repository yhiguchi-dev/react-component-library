package com.example.springboot.presentation.registration;

import com.example.domain.model.user.exception.UserAlreadyExistsException;
import com.example.springboot.application.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

  UserRegistrationService userRegistrationService;

  Logger log = LoggerFactory.getLogger(RegistrationController.class);

  public RegistrationController(UserRegistrationService userRegistrationService) {
    this.userRegistrationService = userRegistrationService;
  }

  @GetMapping
  public String get(Model model) {
    model.addAttribute("registrationRequest", new RegistrationRequest());
    return "registration";
  }

  @PostMapping
  public String post(@Valid @ModelAttribute RegistrationRequest registrationRequest) {
    userRegistrationService.register(
        registrationRequest.getEmail(), registrationRequest.getPassword());
    return "login";
  }

  @ExceptionHandler({UserAlreadyExistsException.class})
  public String handleException(UserAlreadyExistsException e, Model model) {
    log.warn(e.getMessage());
    model.addAttribute("registrationRequest", new RegistrationRequest());
    model.addAttribute("errorMessage", "新規登録に失敗しました");
    return "registration";
  }
}
