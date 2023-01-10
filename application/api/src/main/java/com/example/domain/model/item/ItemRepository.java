package com.example.domain.model.item;

/** 商品リポジトリ */
public interface ItemRepository {

  Item get(ItemIdentifier itemIdentifier);

  Item get(ItemName itemName);
}
