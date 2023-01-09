package com.example.springboot.infrastructure.datasource.item;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.item.ItemName;
import com.example.domain.model.item.ItemRepository;
import com.example.domain.model.item.exception.ItemNotFoundException;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDataSource implements ItemRepository {

  ItemMapper itemMapper;

  public ItemDataSource(ItemMapper itemMapper) {
    this.itemMapper = itemMapper;
  }

  @Override
  public Item get(ItemIdentifier itemIdentifier) {
    Item item = itemMapper.selectById(itemIdentifier);
    if (Objects.isNull(item)) {
      throw new ItemNotFoundException("商品が見つかりません");
    }
    return item;
  }

  @Override
  public Item get(ItemName itemName) {
    Item item = itemMapper.selectByName(itemName);
    if (Objects.isNull(item)) {
      throw new ItemNotFoundException("商品が見つかりません");
    }
    return item;
  }
}
