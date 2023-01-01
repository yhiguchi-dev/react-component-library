package com.example.servlet.presentation.filter;

import java.util.List;

public class ExcludedUrls {

  static List<String> list = List.of("/login", "/registration");

  static boolean contains(String path) {
    return list.contains(path);
  }
}
