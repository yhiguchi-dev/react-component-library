package com.example.domain.model.purchase;

import java.util.Objects;

public class PurchaseIdentifier {
  String value;

  public PurchaseIdentifier(String value) {
    this.value = value;
  }

  PurchaseIdentifier() {}

  public String value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PurchaseIdentifier that = (PurchaseIdentifier) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
