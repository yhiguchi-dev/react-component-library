package com.example.servlet.presentation.home;

import com.example.domain.model.user.*;
import com.example.servlet.presentation.ViewForwardable;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "homeServlet", value = "/home")
public class HomeServlet extends HttpServlet implements ViewForwardable {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    forward("home", request, response);
  }
}
