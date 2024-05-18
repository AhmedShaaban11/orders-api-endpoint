package com.system.apis.dto.order;

import com.system.apis.dto.order.OrderDTO;

import java.util.List;

public class CompoundOrderDTO extends OrderDTO {
  private List<SimpleOrderDTO> orderDTOS;

  public CompoundOrderDTO() {
    this.orderDTOS = null;
  }

  public CompoundOrderDTO(List<SimpleOrderDTO> orderDTOS) {
    this.orderDTOS = orderDTOS;
  }

  public List<SimpleOrderDTO> getOrderDTOS() {
    return orderDTOS;
  }

  @Override
  public String getType() {
    return "compound";
  }
}
