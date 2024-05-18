package com.system.apis.dto.order;

import com.system.apis.controllers.OrderController;
import com.system.apis.models.Product;

import java.util.List;

public abstract class OrderDTO {
  protected final String type;

  public OrderDTO() {
    this.type = getType();
  }

  public abstract String getType();
}
