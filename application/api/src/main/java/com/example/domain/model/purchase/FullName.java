package com.example.domain.model.purchase;

/** 氏名 */
public class FullName {
  FirstName firstName;
  LastName lastName;

  public FullName(FirstName firstName, LastName lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  FullName() {}

  public FirstName firstName() {
    return firstName;
  }

  public LastName lastName() {
    return lastName;
  }

  public String name() {
    return lastName.value() + " " + firstName.value();
  }
}
