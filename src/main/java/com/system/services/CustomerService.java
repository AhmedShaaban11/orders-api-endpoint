package com.system.services;

import com.system.apis.models.Customer;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
  private List<Customer> customers;

  public CustomerService() {
    this.customers = new ArrayList<>();
  }

  public Optional<Customer> getCustomerByUsername(String username) {
    return this.customers.stream()
        .filter(customer -> customer.getUsername().equals(username))
        .findFirst();
  }

  public boolean addCustomer(Customer customer) {
    Optional<Customer> customerOptional = getCustomerByUsername(customer.getUsername());
    // If no customer with the same username exists, add the customer
    if (customerOptional.isEmpty()) {
      this.customers.add(customer);
    }
    return customerOptional.isEmpty();
  }

  public Optional<Customer> getCustomerById(Long id) {
    return this.customers.stream()
        .filter(customer -> customer.getId().equals(id))
        .findFirst();
  }

  public Optional<Customer> loginCustomer(String username, String password) {
    Optional<Customer> customerOptional = getCustomerByUsername(username);
    if (customerOptional.isPresent()) {
      Customer customer = customerOptional.get();
      if (customer.getPassword().equals(password)) {
        return Optional.of(customer);
      }
    }
    return Optional.empty();
  }
}
