package com.example.domain.model.purchase;

/** 住所 */
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

  Address() {}

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
