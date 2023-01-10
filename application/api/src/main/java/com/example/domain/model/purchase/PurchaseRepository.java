package com.example.domain.model.purchase;

import com.example.domain.model.user.UserIdentifier;

/** 購入リポジトリ */
public interface PurchaseRepository {

  void register(Purchase purchase);

  Purchases find(UserIdentifier userIdentifier);
}
