package com.example.servlet.infrastructure.datasource;

import com.example.domain.model.item.*;
import com.example.query.ItemCriteria;
import com.example.query.ItemSummary;
import com.example.query.ItemSummaryRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemSummaryDataSource implements ItemSummaryRepository {

  DatabaseAccessor databaseAccessor;

  public ItemSummaryDataSource(DatabaseAccessor databaseAccessor) {
    this.databaseAccessor = databaseAccessor;
  }

  @Override
  public ItemSummary find(ItemCriteria itemCriteria) {
    int count = selectCount(itemCriteria);
    if (count == 0) {
      return new ItemSummary();
    }
    List<Item> items = selectBy(itemCriteria);
    return new ItemSummary(new Items(items), count);
  }

  List<Item> selectBy(ItemCriteria itemCriteria) {
    String sql =
        """
        SELECT
          id,
          name,
          price
        FROM purchase.item
        WHERE
          name LIKE CONCAT(?, '%')
        ORDER BY id
        LIMIT ?
        OFFSET ?;
        """;
    try (Connection connection = databaseAccessor.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, itemCriteria.itemName());
      statement.setInt(2, itemCriteria.perPage());
      statement.setInt(3, itemCriteria.offset());
      try (ResultSet resultSet = statement.executeQuery()) {
        List<Item> list = new ArrayList<>();
        while (resultSet.next()) {
          Item item =
              new Item(
                  new ItemIdentifier(resultSet.getInt("id")),
                  new ItemName(resultSet.getString("name")),
                  new Price(resultSet.getInt("price")));
          list.add(item);
        }
        return list;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  int selectCount(ItemCriteria itemCriteria) {
    String sql =
        """
        SELECT
          COUNT(*)
        FROM purchase.item
        WHERE
          name LIKE CONCAT(?, '%');
        """;
    try (Connection connection = databaseAccessor.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, itemCriteria.itemName());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          return resultSet.getInt(1);
        }
        return 0;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
