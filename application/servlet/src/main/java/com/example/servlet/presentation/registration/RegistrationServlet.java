package com.example.servlet.presentation.registration;

import com.example.domain.model.user.*;
import com.example.domain.model.user.exception.UserAlreadyExistsException;
import com.example.servlet.application.service.UserRegistrationService;
import com.example.servlet.application.service.user.UserService;
import com.example.servlet.infrastructure.datasource.DatabaseAccessor;
import com.example.servlet.infrastructure.datasource.UserDataSource;
import com.example.servlet.infrastructure.password.MyPasswordEncoder;
import com.example.servlet.presentation.ViewForwardable;
import com.example.servlet.presentation.ViewRedirectable;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet implements ViewForwardable, ViewRedirectable {

  UserRegistrationService userRegistrationService;

  Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

  @Override
  public void init(ServletConfig config) {
    UserRepository userRepository = new UserDataSource(new DatabaseAccessor());
    PasswordEncodable passwordEncodable = new MyPasswordEncoder();
    UserService userService = new UserService(userRepository);
    this.userRegistrationService = new UserRegistrationService(userService, passwordEncodable);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    forward("registration", request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      userRegistrationService.register(email, password);
      redirect("login", request, response);
    } catch (UserAlreadyExistsException e) {
      log.warn(e.getMessage());
      request.setAttribute("errorMessage", "?????????????????????????????????");
      forward("registration", request, response);
    }
  }
}
