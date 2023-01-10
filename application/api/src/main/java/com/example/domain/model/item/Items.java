package com.example.domain.model.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 商品一覧 */
public class Items implements Iterable<Item> {

  List<Item> values;

  public Items(List<Item> values) {
    this.values = values;
  }

  public Items() {
    this(new ArrayList<>());
  }

  @Override
  public Iterator<Item> iterator() {
    return values.iterator();
  }
}
