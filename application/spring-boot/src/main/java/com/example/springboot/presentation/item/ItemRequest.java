package com.example.springboot.presentation.item;

import com.example.query.ItemCriteria;
import com.example.query.Page;
import com.example.query.Pagination;
import com.example.query.PerPage;
import jakarta.validation.constraints.NotBlank;

public class ItemRequest {
  int page = 1;
  @NotBlank String itemName;

  public ItemCriteria toItemCriteria() {
    return new ItemCriteria(itemName, new Pagination(new Page(page), new PerPage(20)));
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
}
