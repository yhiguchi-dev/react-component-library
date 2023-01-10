package com.example.domain.model.item.exception;

/** 商品が見つからない */
public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException(String message) {
    super(message);
  }
}
