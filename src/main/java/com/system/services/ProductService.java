package com.system.services;

import com.system.apis.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  private List<Product> products;

  public ProductService() {
    this.products = List.of(
        new Product("4014F030", "Al-Moasser", "Samir-Ali", "Books", 100, 5),
        new Product("4009C021", "New Gem", "Samir-Ali", "Books", 200, 2),
        new Product("4049D052", "Honey", "Sheikh Al-Asal", "Food", 500, 3),
        new Product("4040E002", "Legion", "Lenovo", "Computers", 2500, 10)
    );
  }

  public List<Product> getProducts() {
    return this.products;
  }

  public Optional<Product> getProductById(Long id) {
    return this.products.stream()
        .filter(product -> product.getId().equals(id))
        .findFirst();
  }
}
