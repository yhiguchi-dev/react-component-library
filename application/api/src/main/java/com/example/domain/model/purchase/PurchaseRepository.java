package com.example.domain.model.purchase;

import com.example.domain.model.user.UserIdentifier;

public interface PurchaseRepository {

  void register(Purchase purchase);

  Purchases find(UserIdentifier userIdentifier);
}
