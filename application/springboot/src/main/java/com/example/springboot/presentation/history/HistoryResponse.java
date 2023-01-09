package com.example.springboot.presentation.history;

import com.example.domain.model.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryResponse {
  @JsonProperty("item_name")
  String itemName;

  @JsonProperty("price")
  int price;

  @JsonProperty("full_name")
  String fullName;

  @JsonProperty("zip_code")
  String zipCode;

  @JsonProperty("prefecture")
  String prefecture;

  @JsonProperty("city")
  String city;

  @JsonProperty("street")
  String street;

  public HistoryResponse(Purchase purchase) {
    this.itemName = purchase.item().itemName().value();
    this.price = purchase.item().price().value();
    this.fullName = purchase.fullName().name();
    this.zipCode = purchase.address().zipCode();
    this.prefecture = purchase.address().prefecture();
    this.city = purchase.address().city();
    this.street = purchase.address().street();
  }
}
