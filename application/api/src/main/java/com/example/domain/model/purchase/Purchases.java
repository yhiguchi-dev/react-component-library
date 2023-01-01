package com.example.domain.model.purchase;

import java.util.Iterator;
import java.util.List;

public class Purchases implements Iterable<Purchase> {

  List<Purchase> values;

  public Purchases(List<Purchase> values) {
    this.values = values;
  }

  @Override
  public Iterator<Purchase> iterator() {
    return values.iterator();
  }
}
