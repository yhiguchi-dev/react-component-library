package com.example.springboot.infrastructure.datasource.item;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.Items;
import com.example.query.ItemCriteria;
import com.example.query.ItemSummary;
import com.example.query.ItemSummaryRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ItemSummaryDataSource implements ItemSummaryRepository {

  ItemMapper itemMapper;

  public ItemSummaryDataSource(ItemMapper itemMapper) {
    this.itemMapper = itemMapper;
  }

  @Override
  public ItemSummary find(ItemCriteria itemCriteria) {
    int count = itemMapper.selectCount(itemCriteria);
    if (count == 0) {
      return new ItemSummary();
    }
    List<Item> items = itemMapper.selectBy(itemCriteria);
    return new ItemSummary(new Items(items), count);
  }
}
