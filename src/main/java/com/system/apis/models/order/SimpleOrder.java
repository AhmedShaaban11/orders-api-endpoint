package com.system.apis.models.order;

import com.system.apis.models.Customer;
import com.system.apis.models.Product;

import java.util.List;

public class SimpleOrder extends Order {
  private final Customer customer;
  private final String location;
  private final List<ProductQuantity> productsQuantities;

  public SimpleOrder(Customer customer, String location, List<ProductQuantity> productsQuantities) {
    this.customer = customer;
    this.location = location;
    this.productsQuantities = productsQuantities;
  }

  public double getTotalPrice() {
    double total = 0;
    for (var productQuantity : productsQuantities) {
      total += productQuantity.getProduct().getPrice() * productQuantity.getQuantity();
    }
    return total;
  }

  @Override
  public boolean isApplicable() {
    // check customer balance
    if (getTotalPrice() > customer.getBalance()) { return false; }
    // check products availability
    for (var productQuantity : productsQuantities) {
      // check product quantity
      if (productQuantity.getQuantity() > productQuantity.getProduct().getQuantity()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean apply() {
    if (!isApplicable()) { return false; }
    customer.decreaseBalance(getTotalPrice());
    for (var productQuantity : productsQuantities) {
      productQuantity.getProduct().decreaseQuantity(productQuantity.getQuantity());
    }
    return true;
  }

  @Override
  public boolean ship(double shippingCost) {
    if (isShipped) { return false; }
    this.customer.decreaseBalance(shippingCost);
    this.isShipped = true;
    return true;
  }

  @Override
  public String getCustomerName() {
    return customer.getUsername();
  }

  public Customer getCustomer() {
    return customer;
  }

  public String getLocation() {
    return location;
  }

  public List<ProductQuantity> getProductsQuantities() {
    return productsQuantities;
  }

}
