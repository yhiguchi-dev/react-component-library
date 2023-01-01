package com.example.springboot.infrastructure.datasource.user;

import com.example.domain.model.user.*;
import com.example.domain.model.user.exception.UserNotFoundException;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataSource implements UserRepository {

  UserMapper userMapper;

  public UserDataSource(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public void register(User user) {}

  @Override
  public User find(EmailAddress emailAddress) {
    User user = userMapper.selectBy(emailAddress);
    if (Objects.isNull(user)) {
      throw new UserNotFoundException("ユーザーが見つかりません");
    }
    return user;
  }
}
