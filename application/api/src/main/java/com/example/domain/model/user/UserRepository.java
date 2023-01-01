package com.example.domain.model.user;

/** ユーザーリポジトリ */
public interface UserRepository {

  void register(User user);

  User find(EmailAddress emailAddress);
}
