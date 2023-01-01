package com.example.servlet.infrastructure.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseAccessor {

  Connection getConnection() {
    try {
      Context context = new InitialContext();
      if (context.lookup("java:comp/env/appDataSource") instanceof DataSource dataSource) {
        return dataSource.getConnection();
      }
      throw new RuntimeException("DB接続設定に誤りがあります");
    } catch (SQLException | NamingException e) {
      throw new RuntimeException(e);
    }
  }
}
