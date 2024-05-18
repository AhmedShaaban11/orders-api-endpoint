package com.system.apis.controllers;

import com.system.apis.dto.order.ShippingDTO;
import com.system.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingController {
  private OrderService orderService;

  public ShippingController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/shipping/simple")
  public boolean shipOrder(@RequestBody ShippingDTO shippingDTO) {
    return orderService.shipSimpleOrder(shippingDTO.getOrderId());
  }

  @PostMapping("/shipping/compound")
  public boolean shipCompoundOrder(@RequestBody ShippingDTO shippingDTO) {
    return orderService.shipCompoundOrder(shippingDTO.getOrderId());
  }

}
