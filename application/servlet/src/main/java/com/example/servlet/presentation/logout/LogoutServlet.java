package com.example.servlet.presentation.logout;

import com.example.servlet.presentation.ViewForwardable;
import com.example.servlet.presentation.ViewRedirectable;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet implements ViewForwardable, ViewRedirectable {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession session = request.getSession();
    session.invalidate();
    redirect("login", request, response);
  }
}
