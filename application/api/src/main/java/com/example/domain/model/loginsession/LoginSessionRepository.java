package com.example.domain.model.loginsession;

/** ログインセッションリポジトリ */
public interface LoginSessionRepository {

  void register(LoginSession loginSession);

  LoginSession find();
}
