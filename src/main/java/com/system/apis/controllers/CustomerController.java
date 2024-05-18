package com.system.apis.controllers;

import com.system.apis.dto.customer.LoginCustomerDTO;
import com.system.apis.dto.customer.RegisterCustomerDTO;
import com.system.apis.models.Customer;
import com.system.services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class CustomerController {
  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping("/customers/login")
  public Optional<Customer> login(@RequestBody LoginCustomerDTO customerInfo) {
    return customerService.loginCustomer(customerInfo.username(), customerInfo.password());
  }

  @PostMapping("/customers/register")
  public Customer register(@RequestBody RegisterCustomerDTO customerInfo) {
    Customer customer = new Customer(customerInfo.username(), customerInfo.password(), customerInfo.balance());
    boolean isAdded = customerService.addCustomer(customer);
    return isAdded ? customer : null;
  }
}
