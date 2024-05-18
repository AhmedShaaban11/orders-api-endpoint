package com.system.apis.controllers;

import com.system.apis.dto.order.CompoundOrderDTO;
import com.system.apis.dto.order.SimpleOrderDTO;
import com.system.apis.mapper.OrderMapper;
import com.system.apis.models.order.CompoundOrder;
import com.system.apis.models.order.Order;
import com.system.apis.models.order.SimpleOrder;
import com.system.services.CustomerService;
import com.system.services.OrderService;
import com.system.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/orders")
  public List<Order> listOrders() {
    return orderService.getOrders();
  }

  @GetMapping("/orders/simple")
  public List<SimpleOrder> listSimpleOrders() {
    return orderService.getSimpleOrders();
  }

  @PostMapping("/orders/simple")
  public Optional<SimpleOrder> addSimpleOrder(@RequestBody SimpleOrderDTO simpleOrderDTO) {
    return orderService.addSimpleOrder(simpleOrderDTO);
  }

  @GetMapping("/orders/compound")
  public List<CompoundOrder> listCompoundOrders() {
    return orderService.getCompoundOrders();
  }

  @PostMapping("/orders/compound")
  public Optional<CompoundOrder> addCompoundOrder(@RequestBody CompoundOrderDTO compoundOrderDTO) {
    return orderService.addCompoundOrder(compoundOrderDTO);
  }

}
