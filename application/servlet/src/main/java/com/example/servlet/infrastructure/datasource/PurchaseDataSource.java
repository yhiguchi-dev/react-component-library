package com.example.servlet.infrastructure.datasource;

import com.example.domain.model.purchase.Purchase;
import com.example.domain.model.purchase.PurchaseRepository;
import com.example.domain.model.purchase.Purchases;
import com.example.domain.model.user.UserIdentifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    return null;
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
                      prefecture,
                      city,
                      street
                    ) VALUES (
                      ?,
                      ?,
                      ?,
                      ?
                    );
                    """;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, purchase.purchaseIdentifier().value());
      statement.setString(2, purchase.address().prefecture());
      statement.setString(3, purchase.address().city());
      statement.setString(4, purchase.address().street());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
