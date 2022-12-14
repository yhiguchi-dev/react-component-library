package com.example.query;

/** 商品検索条件 */
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

  int getPerPage() {
    return perPage();
  }

  int getOffset() {
    return offset();
  }
}
