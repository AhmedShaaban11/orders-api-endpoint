package com.system.services;

import com.system.apis.dto.order.CompoundOrderDTO;
import com.system.apis.dto.order.SimpleOrderDTO;
import com.system.apis.mapper.OrderMapper;
import com.system.apis.models.Product;
import com.system.apis.models.order.CompoundOrder;
import com.system.apis.models.order.Order;
import com.system.apis.models.order.SimpleOrder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
  private List<SimpleOrder> simpleOrders;
  private List<CompoundOrder> compoundOrders;
  private OrderMapper orderMapper;
  private NotificationService notificationService;

  public OrderService(CustomerService customerService, ProductService productService, NotificationService notificationService) {
    this.simpleOrders = new ArrayList<>();
    this.compoundOrders = new ArrayList<>();
    this.orderMapper = new OrderMapper(customerService, productService, this);
    this.notificationService = notificationService;
  }


  public List<SimpleOrder> getSimpleOrders() {
    return simpleOrders;
  }

  public List<CompoundOrder> getCompoundOrders() {
    return compoundOrders;
  }

  public List<Order> getOrders() {
    List<Order> orders = new ArrayList<>();
    orders.addAll(this.simpleOrders);
    orders.addAll(this.compoundOrders);
    return orders;
  }

  public Optional<SimpleOrder> addSimpleOrder(SimpleOrderDTO order) {
    Map<String, String> notificationPlaceholders = new HashMap<>();
    notificationPlaceholders.put("customerName", "Unknown");
    Optional<SimpleOrder> simpleOrderOptional = orderMapper.mapToSimpleOrder(order);
    if (simpleOrderOptional.isEmpty()) {
      this.notificationService.sendNotification("placeOrderFailure", notificationPlaceholders);
      return Optional.empty();
    }
    SimpleOrder simpleOrder = simpleOrderOptional.get();
    boolean isApplied = simpleOrder.apply();
    if (isApplied) { this.simpleOrders.add(simpleOrder); }
    notificationPlaceholders.put("customerName", simpleOrder.getCustomerName());
    String notificationName = isApplied ? "placeOrderSuccess" : "placeOrderFailure";
    this.notificationService.sendNotification(notificationName, notificationPlaceholders);
    return isApplied ? simpleOrderOptional : Optional.empty();
  }

  public Optional<CompoundOrder> addCompoundOrder(CompoundOrderDTO order) {
    Map<String, String> notificationPlaceholders = new HashMap<>();
    notificationPlaceholders.put("customerName", "Unknown");
    Optional<CompoundOrder> compoundOrderOptional = orderMapper.mapToCompoundOrder(order);
    if (compoundOrderOptional.isEmpty()) {
      this.notificationService.sendNotification("placeOrderFailure", notificationPlaceholders);
      return Optional.empty();
    }
    CompoundOrder compoundOrder = compoundOrderOptional.get();
    boolean isApplied = compoundOrder.apply();
    if (isApplied) { this.compoundOrders.add(compoundOrder); }
    String notificationName = isApplied ? "placeOrderSuccess" : "placeOrderFailure";
    notificationPlaceholders.put("customerName", compoundOrder.getCustomerName());
    this.notificationService.sendNotification(notificationName, notificationPlaceholders);
    return isApplied ? compoundOrderOptional : Optional.empty();
  }

  public Optional<Order> getOrderById(Long id) {
    return getOrders().stream()
        .filter(order -> order.getId().equals(id))
        .findFirst();
  }

  public boolean shipSimpleOrder(Long id) {
    Optional<SimpleOrder> simpleOrderOptional = this.simpleOrders.stream()
        .filter(order -> order.getId().equals(id))
        .findFirst();
    Map<String, String> notificationPlaceholders = new HashMap<>();
    notificationPlaceholders.put("customerName", "Unknown");
    if (simpleOrderOptional.isEmpty()) {
      this.notificationService.sendNotification("shipOrderFailure", notificationPlaceholders);
      return false;
    }
    SimpleOrder simpleOrder = simpleOrderOptional.get();
    notificationPlaceholders.put("customerName", simpleOrder.getCustomerName());
    boolean isShipped = simpleOrder.ship(10);
    String notificationName = isShipped ? "shipOrderSuccess" : "shipOrderFailure";
    this.notificationService.sendNotification(notificationName, notificationPlaceholders);
    return isShipped;
  }

  public boolean shipCompoundOrder(Long id) {
    Optional<CompoundOrder> compoundOrderOptional = this.compoundOrders.stream()
        .filter(order -> order.getId().equals(id))
        .findFirst();
    Map<String, String> notificationPlaceholders = new HashMap<>();
    notificationPlaceholders.put("customerName", "Unknown");
    if (compoundOrderOptional.isEmpty()) {
      this.notificationService.sendNotification("shipOrderFailure", notificationPlaceholders);
      return false;
    }
    CompoundOrder compoundOrder = compoundOrderOptional.get();
    notificationPlaceholders.put("customerName", compoundOrder.getCustomerName());
    boolean isShipped = compoundOrder.ship(10);
    String notificationName = isShipped ? "shipOrderSuccess" : "shipOrderFailure";
    this.notificationService.sendNotification(notificationName, notificationPlaceholders);
    return isShipped;
  }
}
