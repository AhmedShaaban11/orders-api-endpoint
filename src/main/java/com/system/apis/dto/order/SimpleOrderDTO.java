package com.system.apis.dto.order;

import java.util.List;

public class SimpleOrderDTO extends OrderDTO {
  private final Long customerId;
  private final String location;
  private final List<ProductQuantityDTO> productQuantityDTOS;

  public SimpleOrderDTO(Long customerId, String location, List<ProductQuantityDTO> productQuantityDTOS) {
    this.customerId = customerId;
    this.location = location;
    this.productQuantityDTOS = productQuantityDTOS;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public String getLocation() {
    return location;
  }

  public List<ProductQuantityDTO> getProductQuantityDTOS() {
    return productQuantityDTOS;
  }

  @Override
  public String getType() {
    return "simple";
  }
}
