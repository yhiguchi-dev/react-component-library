package com.example.query;

import com.example.domain.model.item.Items;

public class ItemSummary {
  Items items;
  int totalCount;

  public ItemSummary(Items items, int totalCount) {
    this.items = items;
    this.totalCount = totalCount;
  }

  public ItemSummary() {
    this(new Items(), 0);
  }

  public Items items() {
    return items;
  }

  public int totalCount() {
    return totalCount;
  }
}
