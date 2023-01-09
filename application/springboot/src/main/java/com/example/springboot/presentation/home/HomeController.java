package com.example.springboot.presentation.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

  @GetMapping
  public String get() {
    return "home";
  }
}
