package com.example.classicmodlesslaes.service.interfaces;

import com.example.classicmodlesslaes.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    // Basic CRUD
    Product findProductById(String id);
    Product saveProduct(Product product);
    Product updatedProduct(Product product);
    void deleteProduct(String id);

    // Specific queries
    List<Product> findProductsByName(String name);
    List<Product> findProductsBelowStock(int stockLevel);
    List<Product> findProductsByVendor(String vendor);
    List<Product> findProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
