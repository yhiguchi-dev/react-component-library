package com.example.servlet.infrastructure.datasource;

import com.example.domain.model.user.*;
import java.sql.*;

public class UserDataSource implements UserRepository {

  DatabaseAccessor databaseAccessor;

  public UserDataSource(DatabaseAccessor databaseAccessor) {
    this.databaseAccessor = databaseAccessor;
  }

  @Override
  public void register(User user) {
    insert(user);
  }

  @Override
  public User find(EmailAddress emailAddress) {
    return selectBy(emailAddress);
  }

  void insert(User user) {
    String sql =
        """
        INSERT INTO purchase.user(
          id,
          email_address,
          password
        ) VALUES (
          ?,
          ?,
          ?
        );
        """;
    try (Connection connection = databaseAccessor.getConnection()) {
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, user.userIdentifier().value());
        statement.setString(2, user.emailAddress().value());
        statement.setString(3, user.password().value());
        statement.executeUpdate();
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw new RuntimeException(e);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  User selectBy(EmailAddress emailAddress) {
    String sql =
        """
        SELECT
          id,
          email_address,
          password
        FROM purchase.user
        WHERE
          email_address = ?;
        """;
    try (Connection connection = databaseAccessor.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, emailAddress.value());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          return new User(
              new UserIdentifier(resultSet.getString("id")),
              new EmailAddress(resultSet.getString("email_address")),
              new Password(resultSet.getString("password")));
        }
        return new User();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
