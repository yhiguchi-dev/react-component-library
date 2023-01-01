package com.example.servlet.presentation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ViewForwardable {
  default void forward(String view, HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = String.format("/WEB-INF/view/%s.jsp", view);
    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    dispatcher.forward(request, response);
  }
}
