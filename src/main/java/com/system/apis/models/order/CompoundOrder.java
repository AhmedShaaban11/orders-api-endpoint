package com.system.apis.models.order;

import com.system.apis.models.Customer;
import com.system.apis.models.Product;

import java.util.List;

public class CompoundOrder extends Order {
  private List<Order> orders;

  public CompoundOrder(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public boolean isApplicable() {
    for (var order : orders) {
      if (!order.isApplicable()) { return false; }
    }
    return true;
  }

  @Override
  public boolean apply() {
    // check if all sub orders are applicable
    if (!isApplicable()) { return false; }
    // apply all sub orders
    for (var order : orders) { order.apply(); }
    return true;
  }

  @Override
  public boolean ship(double shippingCost) {
    if (isShipped) { return false; }
    for (var order : orders) {
      order.ship(shippingCost / orders.size());
    }
    this.isShipped = true;
    return true;
  }

  public List<Order> getSubOrders() {
    return orders;
  }

  public void setSubOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public String getCustomerName() {
    StringBuilder sb = new StringBuilder();
    sb.append(orders.get(0).getCustomerName());
    for (int i = 1; i < orders.size(); ++i) {
      sb.append(", ").append(orders.get(i).getCustomerName());
    }
    return sb.toString();
  }
}
