package com.system.apis.models.order;

import com.system.apis.dto.order.OrderDTO;
import com.system.apis.models.Customer;
import com.system.apis.models.Product;

import javax.lang.model.element.Name;
import java.util.List;

public abstract class Order {
  private static Long idCounter = 1L;
  private final Long id;
  protected boolean isShipped;

  public Order() {
    this.id = idCounter++;
    this.isShipped = false;
  }

  public Long getId() {
    return id;
  }

  public boolean isShipped() {
    return isShipped;
  }

  public abstract boolean isApplicable();

  public abstract boolean apply();

  public abstract boolean ship(double shippingCost);

  public abstract String getCustomerName();

}
