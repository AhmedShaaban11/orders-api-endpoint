package com.system.apis.models;

public class Product {
  private static Long idCounter = 1L;
  private final Long id;
  private final String serialNumber;
  private String name;
  private String vendor;
  private String Category;
  private double price;
  private double quantity;

  public Product(String serialNumber, String name, String vendor, String Category, double price, double quantity) {
    this.id = idCounter++;
    this.serialNumber = serialNumber;
    this.name = name;
    this.vendor = vendor;
    this.Category = Category;
    this.price = price;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getCategory() {
    return Category;
  }

  public void setCategory(String category) {
    Category = category;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public void increaseQuantity(double quantity) {
    this.quantity += quantity;
  }

  public void decreaseQuantity(double quantity) {
    this.quantity -= quantity;
    if (this.quantity < 0) {
      this.quantity = 0;
    }
  }
}
