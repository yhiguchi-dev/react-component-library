package com.example.domain.model.loginsession;

public class SessionVerifier {
  LoginSession loginSession;

  public SessionVerifier(LoginSession loginSession) {
    this.loginSession = loginSession;
  }

  public void verify() {
    throwExceptionIfLoginSessionNotExists(loginSession);
    throwExceptionIfLoginSessionExpired(loginSession);
  }

  void throwExceptionIfLoginSessionNotExists(LoginSession loginSession) {
    if (loginSession.exists()) {
      throw new LoginSessionNotFoundException("セッションが見つかりません");
    }
  }

  void throwExceptionIfLoginSessionExpired(LoginSession loginSession) {
    if (loginSession.isExpired()) {
      throw new LoginSessionExpiredException(
          String.format("セッションが有効期限切れです :%s", loginSession.loginSessionIdentifier().value()));
    }
  }
}
