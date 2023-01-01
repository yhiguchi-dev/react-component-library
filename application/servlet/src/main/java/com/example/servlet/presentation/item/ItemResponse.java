package com.example.servlet.presentation.item;

import com.example.domain.model.item.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemResponse {
  @JsonProperty("item_name")
  String itemName;

  @JsonProperty("price")
  int price;

  public ItemResponse(Item item) {
    this.itemName = item.itemName().value();
    this.price = item.price().value();
  }
}
