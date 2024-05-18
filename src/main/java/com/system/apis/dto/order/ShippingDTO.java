package com.system.apis.dto.order;

public class ShippingDTO {
  private Long orderId;

  public ShippingDTO() {
  }

  public ShippingDTO(Long orderId) {
    this.orderId = orderId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

}
