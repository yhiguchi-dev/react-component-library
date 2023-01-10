package com.example.query;

/** 商品サマリリポジトリ */
public interface ItemSummaryRepository {

  ItemSummary find(ItemCriteria itemCriteria);
}
