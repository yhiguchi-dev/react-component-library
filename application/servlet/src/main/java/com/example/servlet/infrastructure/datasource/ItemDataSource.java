package com.example.servlet.infrastructure.datasource;

import com.example.domain.model.item.*;
import com.example.domain.model.item.exception.ItemNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDataSource implements ItemRepository {

  DatabaseAccessor databaseAccessor;

  public ItemDataSource(DatabaseAccessor databaseAccessor) {
    this.databaseAccessor = databaseAccessor;
  }

  @Override
  public Item get(ItemIdentifier itemIdentifier) {
    return selectBy(itemIdentifier);
  }

  @Override
  public Item get(ItemName itemName) {
    return selectBy(itemName);
  }

  Item selectBy(ItemIdentifier itemIdentifier) {
    String sql =
        """
              SELECT
                id,
                name,
                price
              FROM purchase.item
              WHERE
                id = ?;
              """;
    try (Connection connection = databaseAccessor.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, itemIdentifier.value());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          return new Item(
              new ItemIdentifier(resultSet.getInt("id")),
              new ItemName(resultSet.getString("name")),
              new Price(resultSet.getInt("price")));
        }
        throw new ItemNotFoundException("商品が見つかりません");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  Item selectBy(ItemName itemName) {
    String sql =
        """
        SELECT
          id,
          name,
          price
        FROM purchase.item
        WHERE
          name = ?;
        """;
    try (Connection connection = databaseAccessor.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, itemName.value());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          return new Item(
              new ItemIdentifier(resultSet.getInt("id")),
              new ItemName(resultSet.getString("name")),
              new Price(resultSet.getInt("price")));
        }
        throw new ItemNotFoundException("商品が見つかりません");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
