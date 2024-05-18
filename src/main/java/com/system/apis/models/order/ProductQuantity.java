package com.system.apis.models.order;

import com.system.apis.models.Product;

public class ProductQuantity {
  private Product product;
  private double quantity;

  public ProductQuantity(Product product, double quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public double getQuantity() {
    return quantity;
  }
}
