package com.example.springboot.presentation.purchase;

import com.example.domain.model.purchase.Address;
import com.example.domain.model.purchase.FirstName;
import com.example.domain.model.purchase.FullName;
import com.example.domain.model.purchase.LastName;

public class PurchasePostRequest {

  String lastName;
  String firstName;
  String zipCode;
  String address;
  String address2;
  String address3;

  public FullName toFullName() {
    return new FullName(new FirstName(firstName), new LastName(lastName));
  }

  public Address toAddress() {
    return new Address(zipCode, address, address2, address3);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getAddress3() {
    return address3;
  }

  public void setAddress3(String address3) {
    this.address3 = address3;
  }
}
