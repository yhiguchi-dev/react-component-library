package com.example.servlet.infrastructure.datasource;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.item.ItemName;
import com.example.domain.model.item.Price;
import com.example.domain.model.purchase.*;
import com.example.domain.model.user.UserIdentifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDataSource implements PurchaseRepository {
  DatabaseAccessor databaseAccessor;

  public PurchaseDataSource(DatabaseAccessor databaseAccessor) {
    this.databaseAccessor = databaseAccessor;
  }

  @Override
  public void register(Purchase purchase) {
    try (Connection connection = databaseAccessor.getConnection()) {
      try {
        insertTransaction(connection, purchase);
        insertAddress(connection, purchase);
        insertIdentity(connection, purchase);
        connection.commit();
      } catch (Exception e) {
        connection.rollback();
        throw e;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Purchases find(UserIdentifier userIdentifier) {
    List<Purchase> purchases = selectBy(userIdentifier);
    if (purchases.isEmpty()) {
      return new Purchases();
    }
    return new Purchases(purchases);
  }

  void insertTransaction(Connection connection, Purchase purchase) {
    String sql =
        """
                    INSERT INTO purchase.transaction(
                      id,
                      user_id,
                      item_id
                    ) VALUES (
                      ?,
                      ?,
                      ?
                    );
                    """;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, purchase.purchaseIdentifier().value());
      statement.setString(2, purchase.userIdentifier().value());
      statement.setInt(3, purchase.item().itemIdentifier().value());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  void insertAddress(Connection connection, Purchase purchase) {
    String sql =
        """
                    INSERT INTO purchase.address(
                      transaction_id,
                      zip_code,
                      prefecture,
                      city,
                      street
                    ) VALUES (
                      ?,
                      ?,
                      ?,
                      ?,
                      ?
                    );
                    """;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, purchase.purchaseIdentifier().value());
      statement.setString(2, purchase.address().zipCode());
      statement.setString(3, purchase.address().prefecture());
      statement.setString(4, purchase.address().city());
      statement.setString(5, purchase.address().street());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  void insertIdentity(Connection connection, Purchase purchase) {
    String sql =
        """
                        INSERT INTO purchase.identity(
                          transaction_id,
                          first_name,
                          last_name
                        ) VALUES (
                          ?,
                          ?,
                          ?
                        );
                        """;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, purchase.purchaseIdentifier().value());
      statement.setString(2, purchase.fullName().firstName().value());
      statement.setString(3, purchase.fullName().lastName().value());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  List<Purchase> selectBy(UserIdentifier userIdentifier) {
    String sql =
        """
          SELECT
            transaction.id,
            user_id,
            item_id,
            item.name AS item_name,
            item.price AS item_price,
            address.zip_code,
            address.prefecture,
            address.city,
            address.street,
            identity.first_name,
            identity.last_name,
            price
          FROM purchase.transaction
          JOIN purchase.item
            ON transaction.item_id = item.id
          JOIN purchase.address
           ON transaction.id = address.transaction_id
          JOIN purchase.identity
            ON transaction.id = identity.transaction_id
          WHERE
            transaction.user_id = ?
          ORDER BY transaction.created_at
          """;
    try (Connection connection = databaseAccessor.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, userIdentifier.value());
      try (ResultSet resultSet = statement.executeQuery()) {
        List<Purchase> list = new ArrayList<>();
        while (resultSet.next()) {
          Item item =
              new Item(
                  new ItemIdentifier(resultSet.getInt("item_id")),
                  new ItemName(resultSet.getString("item_name")),
                  new Price(resultSet.getInt("item_price")));
          FullName fullName =
              new FullName(
                  new FirstName(resultSet.getString("first_name")),
                  new LastName(resultSet.getString("last_name")));
          Address address =
              new Address(
                  resultSet.getString("zip_code"),
                  resultSet.getString("prefecture"),
                  resultSet.getString("city"),
                  resultSet.getString("street"));
          Purchase purchase =
              new Purchase(
                  new PurchaseIdentifier(resultSet.getString("id")),
                  new UserIdentifier(resultSet.getString("user_id")),
                  item,
                  fullName,
                  address);
          list.add(purchase);
        }
        return list;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
