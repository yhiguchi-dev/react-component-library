package com.example.servlet.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ViewRedirectable {

  default void redirect(String view, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.sendRedirect(String.format("%s/%s", request.getContextPath(), view));
  }
}
