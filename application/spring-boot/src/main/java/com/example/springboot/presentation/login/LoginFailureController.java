package com.example.springboot.presentation.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/failure")
public class LoginFailureController {

  @GetMapping
  public String get(Model model) {
    model.addAttribute("loginRequest", new LoginRequest());
    model.addAttribute("errorMessage", "ログインに失敗しました");
    return "login";
  }
}
