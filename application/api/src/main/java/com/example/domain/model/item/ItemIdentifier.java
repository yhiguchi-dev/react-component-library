package com.example.domain.model.item;

import java.util.Objects;

public class ItemIdentifier {
  int value;

  public ItemIdentifier(int value) {
    this.value = value;
  }

  ItemIdentifier() {}

  public int value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemIdentifier that = (ItemIdentifier) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
