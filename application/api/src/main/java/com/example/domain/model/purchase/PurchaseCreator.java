package com.example.domain.model.purchase;

import com.example.domain.model.item.Item;
import com.example.domain.model.user.UserIdentifier;
import java.util.UUID;

public class PurchaseCreator {

  public static Purchase create(
      UserIdentifier userIdentifier, Item item, FullName fullName, Address address) {
    PurchaseIdentifier purchaseIdentifier = new PurchaseIdentifier(UUID.randomUUID().toString());
    return new Purchase(purchaseIdentifier, userIdentifier, item, fullName, address);
  }
}
