package com.example.query;

public class ItemCriteria {
  String itemName;

  Pagination pagination;

  public ItemCriteria(String itemName, Pagination pagination) {
    this.itemName = itemName;
    this.pagination = pagination;
  }

  public int offset() {
    return pagination.offset();
  }

  public int perPage() {
    return pagination.perPage();
  }

  public String itemName() {
    return itemName;
  }

  public int page() {
    return pagination.page();
  }
}
