package com.example.domain.model.purchase;

public class Address {
  String zipCode;
  String prefecture;
  String city;
  String street;

  public Address(String zipCode, String prefecture, String city, String street) {
    this.zipCode = zipCode;
    this.prefecture = prefecture;
    this.city = city;
    this.street = street;
  }

  public String zipCode() {
    return zipCode;
  }

  public String prefecture() {
    return prefecture;
  }

  public String city() {
    return city;
  }

  public String street() {
    return street;
  }
}
