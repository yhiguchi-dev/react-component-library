package com.example.springboot.presentation.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/success")
public class LoginSuccessController {

  @PostMapping
  public String post() {
    return "redirect:/home";
  }
}
