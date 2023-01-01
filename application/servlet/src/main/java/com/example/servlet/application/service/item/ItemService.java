package com.example.servlet.application.service.item;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.item.ItemName;
import com.example.domain.model.item.ItemRepository;

public class ItemService {

  ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Item get(ItemIdentifier itemIdentifier) {
    return itemRepository.get(itemIdentifier);
  }

  public Item get(ItemName itemName) {
    return itemRepository.get(itemName);
  }
}
