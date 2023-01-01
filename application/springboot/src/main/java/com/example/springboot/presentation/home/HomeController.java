package com.example.springboot.presentation.home;

import com.example.springboot.application.service.loginsession.LoginSessionService;
import com.example.springboot.presentation.login.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

  LoginSessionService loginSessionService;
  Logger log = LoggerFactory.getLogger(HomeController.class);

  public HomeController(LoginSessionService loginSessionService) {
    this.loginSessionService = loginSessionService;
  }

  @PostMapping
  public String post(@Validated @ModelAttribute LoginRequest loginRequest, Model model) {
    log.debug(loginRequest.getEmail());
    log.debug(loginRequest.getPassword());

    //    loginSessionService.register();
    return "home";
  }

  @ExceptionHandler({BindException.class})
  public String handleException(BindException e) {
    log.debug("hogeo");
    return "login";
  }
}
