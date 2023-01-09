package com.example.springboot.presentation.login;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

  @GetMapping
  public String get(Model model) {
    model.addAttribute("loginRequest", new LoginRequest());
    return "login";
  }

  @PostMapping
  public String post(
      @Valid @ModelAttribute LoginRequest request, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("errorMessage", "ログインに失敗しました");
      return "login";
    }
    return "forward:/login/processing";
  }
}
