package com.example.domain.model.purchase;

public class FullName {
  FirstName firstName;
  LastName lastName;

  public FullName(FirstName firstName, LastName lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String name() {
    return lastName.value() + firstName.value();
  }
}
