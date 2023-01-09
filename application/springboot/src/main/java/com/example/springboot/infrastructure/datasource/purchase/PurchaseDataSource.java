package com.example.springboot.infrastructure.datasource.purchase;

import com.example.domain.model.purchase.Purchase;
import com.example.domain.model.purchase.PurchaseRepository;
import com.example.domain.model.purchase.Purchases;
import com.example.domain.model.user.UserIdentifier;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseDataSource implements PurchaseRepository {

  PurchaseMapper purchaseMapper;

  public PurchaseDataSource(PurchaseMapper purchaseMapper) {
    this.purchaseMapper = purchaseMapper;
  }

  @Override
  public void register(Purchase purchase) {
    purchaseMapper.insertTransaction(purchase);
    purchaseMapper.insertAddress(purchase);
    purchaseMapper.insertIdentity(purchase);
  }

  @Override
  public Purchases find(UserIdentifier userIdentifier) {
    List<Purchase> purchases = purchaseMapper.selectBy(userIdentifier);
    if (purchases.isEmpty()) {
      return new Purchases();
    }
    return new Purchases(purchases);
  }
}
