package com.example.servlet.presentation.login;

import com.example.domain.model.authentication.exception.AuthenticationFailureException;
import com.example.domain.model.user.PasswordEncodable;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserRepository;
import com.example.servlet.application.service.PasswordAuthenticationService;
import com.example.servlet.application.service.user.UserService;
import com.example.servlet.infrastructure.datasource.DatabaseAccessor;
import com.example.servlet.infrastructure.datasource.UserDataSource;
import com.example.servlet.infrastructure.password.MyPasswordEncoder;
import com.example.servlet.presentation.ViewForwardable;
import com.example.servlet.presentation.ViewRedirectable;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet implements ViewForwardable, ViewRedirectable {

  PasswordAuthenticationService passwordAuthenticationService;

  @Override
  public void init(ServletConfig config) {
    UserRepository userRepository = new UserDataSource(new DatabaseAccessor());
    PasswordEncodable passwordEncodable = new MyPasswordEncoder();
    UserService userService = new UserService(userRepository, passwordEncodable);
    this.passwordAuthenticationService =
        new PasswordAuthenticationService(userService, passwordEncodable);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    forward("login", request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      User authenticatedUser = passwordAuthenticationService.authenticate(email, password);
      HttpSession session = request.getSession(true);
      session.setAttribute("userIdentifier", authenticatedUser.userIdentifier().value());
      redirect("home", request, response);
    } catch (AuthenticationFailureException e) {
      request.setAttribute("errorMessage", "ログインに失敗しました");
      forward("login", request, response);
    }
  }
}
