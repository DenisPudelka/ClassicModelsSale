package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.model.ProductLine;

public interface ProductRepository {
    Product findProductById(String id);
    void saveProduct(Product product);
    void updatedProduct(Product product);
    void deleteProduct(String id);
}
