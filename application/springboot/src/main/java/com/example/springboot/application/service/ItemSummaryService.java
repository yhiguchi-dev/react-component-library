package com.example.springboot.application.service;

import com.example.query.ItemCriteria;
import com.example.query.ItemSummary;
import com.example.query.ItemSummaryRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemSummaryService {

  ItemSummaryRepository itemSummaryRepository;

  public ItemSummaryService(ItemSummaryRepository itemSummaryRepository) {
    this.itemSummaryRepository = itemSummaryRepository;
  }

  public ItemSummary find(ItemCriteria itemCriteria) {
    return itemSummaryRepository.find(itemCriteria);
  }
}
