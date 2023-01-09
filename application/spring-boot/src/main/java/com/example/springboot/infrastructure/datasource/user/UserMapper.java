package com.example.springboot.infrastructure.datasource.user;

import com.example.domain.model.user.EmailAddress;
import com.example.domain.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  void insert(@Param("user") User user);

  User selectBy(@Param("emailAddress") EmailAddress emailAddress);
}
