package com.example.springboot.presentation.purchase;

import com.example.domain.model.item.ItemName;
import jakarta.validation.constraints.NotNull;

public class PurchaseGetRequest {
  @NotNull String itemName;
  @NotNull Integer price;

  public ItemName toItemName() {
    return new ItemName(itemName);
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
