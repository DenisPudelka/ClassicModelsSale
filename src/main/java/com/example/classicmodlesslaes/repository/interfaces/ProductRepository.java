package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.model.ProductLine;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {
    // Basic CRUD
    List<Product> getAllProducts();
    Product findProductById(String id);
    Product saveProduct(Product product);
    Product updatedProduct(Product product);
    boolean deleteProduct(String id);

    // Specific queries
    List<Product> findProductsByName(String name);
    List<Product> findProductsBelowStock(int stockLevel);
    List<Product> findProductsByVendor(String vendor);
    List<Product> findProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
