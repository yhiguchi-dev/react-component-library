package com.example.springboot.infrastructure.datasource.loginsession;

import com.example.domain.model.loginsession.LoginSession;
import com.example.domain.model.loginsession.LoginSessionCreator;
import com.example.domain.model.loginsession.LoginSessionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;

@Repository
public class LoginSessionDataSource implements LoginSessionRepository {

  HttpSession httpSession;

  public LoginSessionDataSource(HttpSession httpSession) {
    this.httpSession = httpSession;
  }

  @Override
  public void register(LoginSession loginSession) {
    httpSession.setAttribute("userId", loginSession.userIdentifier());
    httpSession.setMaxInactiveInterval(loginSession.inactiveInterval());
  }

  @Override
  public LoginSession find() {
    String id = httpSession.getId();
    String userId = httpSession.getAttribute("userId").toString();
    long creationTime = httpSession.getCreationTime();
    long lastAccessedTime = httpSession.getLastAccessedTime();
    int maxInactiveInterval = httpSession.getMaxInactiveInterval();
    return LoginSessionCreator.create(
        id, userId, creationTime, lastAccessedTime, maxInactiveInterval);
  }
}
