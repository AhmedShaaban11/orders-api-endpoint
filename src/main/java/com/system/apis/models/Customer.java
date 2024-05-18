package com.system.apis.models;

public class Customer {
  private static Long idCounter = 1L;
  private final Long id;
  private final String username;
  private String password;
  private double balance;

  public Customer(String username, String password, double balance) {
    this.id = idCounter++;
    this.username = username;
    this.password = password;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public double getBalance() {
    return balance;
  }

  public void decreaseBalance(double amount) {
    this.balance -= amount;
    if (this.balance < 0) {
      this.balance = 0;
    }
  }
}
