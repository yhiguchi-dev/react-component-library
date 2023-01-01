package com.example.domain.model.purchase;

import com.example.domain.model.item.Item;
import com.example.domain.model.user.UserIdentifier;

public class Purchase {
  PurchaseIdentifier purchaseIdentifier;

  UserIdentifier userIdentifier;
  Item item;
  FullName fullName;
  Address address;

  public Purchase(
      PurchaseIdentifier purchaseIdentifier,
      UserIdentifier userIdentifier,
      Item item,
      FullName fullName,
      Address address) {
    this.purchaseIdentifier = purchaseIdentifier;
    this.userIdentifier = userIdentifier;
    this.item = item;
    this.fullName = fullName;
    this.address = address;
  }

  public PurchaseIdentifier purchaseIdentifier() {
    return purchaseIdentifier;
  }

  public UserIdentifier userIdentifier() {
    return userIdentifier;
  }

  public Item item() {
    return item;
  }

  public FullName fullName() {
    return fullName;
  }

  public Address address() {
    return address;
  }
}
