package com.example.domain.model.item;

public class Item {
  ItemIdentifier itemIdentifier;
  ItemName itemName;
  Price price;

  public Item(ItemIdentifier itemIdentifier, ItemName itemName, Price price) {
    this.itemIdentifier = itemIdentifier;
    this.itemName = itemName;
    this.price = price;
  }

  Item() {}

  public ItemIdentifier itemIdentifier() {
    return itemIdentifier;
  }

  public ItemName itemName() {
    return itemName;
  }

  public Price price() {
    return price;
  }
}
