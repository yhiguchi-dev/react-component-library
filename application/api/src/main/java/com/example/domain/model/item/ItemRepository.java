package com.example.domain.model.item;

public interface ItemRepository {

  Item get(ItemIdentifier itemIdentifier);

  Item get(ItemName itemName);
}
