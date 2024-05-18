package com.system.apis.controllers;

import com.system.apis.models.Product;
import com.system.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public List<Product> getProducts() {
    return productService.getProducts();
  }

}
