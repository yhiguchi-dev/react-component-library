package com.example.query;

public interface ItemSummaryRepository {

  ItemSummary find(ItemCriteria itemCriteria);
}
