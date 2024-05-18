package com.system.apis.dto.order;

public class ProductQuantityDTO {
  private Long productId;
  private double quantity;

  public ProductQuantityDTO(Long productId, double quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public double getQuantity() {
    return quantity;
  }
}
