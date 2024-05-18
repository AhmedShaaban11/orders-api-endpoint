package com.system.apis.mapper;

import com.system.apis.dto.order.CompoundOrderDTO;
import com.system.apis.dto.order.OrderDTO;
import com.system.apis.dto.order.SimpleOrderDTO;
import com.system.apis.models.Customer;
import com.system.apis.models.Product;
import com.system.apis.models.order.CompoundOrder;
import com.system.apis.models.order.Order;
import com.system.apis.models.order.ProductQuantity;
import com.system.apis.models.order.SimpleOrder;
import com.system.services.CustomerService;
import com.system.services.OrderService;
import com.system.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderMapper {
  private CustomerService customerService;
  private ProductService productService;
  private OrderService orderService;

  public OrderMapper(CustomerService customerService, ProductService productService, OrderService orderService) {
    this.customerService = customerService;
    this.productService = productService;
    this.orderService = orderService;
  }

  public Optional<SimpleOrder> mapToSimpleOrder(SimpleOrderDTO simpleOrderDTO) {
    Optional<Customer> customer = customerService.getCustomerById(simpleOrderDTO.getCustomerId());
    if (customer.isEmpty()) {
      return Optional.empty();
    }
    List<ProductQuantity> productsQuantities = new ArrayList<>();
    for (var productQuantityDTO : simpleOrderDTO.getProductQuantityDTOS()) {
      Optional<Product> product = productService.getProductById(productQuantityDTO.getProductId());
      if (product.isEmpty()) {
        return Optional.empty();
      }
      productsQuantities.add(new ProductQuantity(product.get(), productQuantityDTO.getQuantity()));
    }
    SimpleOrder simpleOrder = new SimpleOrder(customer.get(), simpleOrderDTO.getLocation(), productsQuantities);
    return Optional.of(simpleOrder);
  }

  private Optional<Order> mapToOrder(OrderDTO orderDTO) {
    if (orderDTO.getType().equals("simple")) {
      var simpleOrder = mapToSimpleOrder((SimpleOrderDTO) orderDTO);
      if (simpleOrder.isPresent()) {
        return Optional.of(simpleOrder.get());
      }
    } else if (orderDTO.getType().equals("compound")) {
      var compoundOrder = mapToCompoundOrder((CompoundOrderDTO) orderDTO);
      if (compoundOrder.isPresent()) {
        return Optional.of(compoundOrder.get());
      }
    }
    return Optional.empty();
  }

  public Optional<CompoundOrder> mapToCompoundOrder(CompoundOrderDTO compoundOrderDTO) {
    List<Order> orders = new ArrayList<>();
    for (var orderDTO : compoundOrderDTO.getOrderDTOS()) {
      Optional<Order> order = mapToOrder(orderDTO);
      if (order.isEmpty()) {
        return Optional.empty();
      }
      orders.add(order.get());
    }
    CompoundOrder compoundOrder = new CompoundOrder(orders);
    return Optional.of(compoundOrder);
  }

}
