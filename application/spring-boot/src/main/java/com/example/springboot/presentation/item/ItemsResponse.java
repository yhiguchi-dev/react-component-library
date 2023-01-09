package com.example.springboot.presentation.item;

import com.example.query.ItemCriteria;
import com.example.query.ItemSummary;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.StreamSupport;

public class ItemsResponse {
  @JsonProperty("total_count")
  int totalCount;

  @JsonProperty("current_page")
  int currentPage;

  @JsonProperty("per_page")
  int perPage;

  @JsonProperty("item_name")
  String itemName;

  @JsonProperty("items")
  List<ItemResponse> items;

  public ItemsResponse(ItemSummary itemSummary, ItemCriteria itemCriteria) {
    this.totalCount = itemSummary.totalCount();
    this.perPage = itemCriteria.perPage();
    this.currentPage = itemCriteria.page();
    this.itemName = itemCriteria.itemName();
    this.items =
        StreamSupport.stream(itemSummary.items().spliterator(), false)
            .map(ItemResponse::new)
            .toList();
  }
}
