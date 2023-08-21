package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.repository.interfaces.ProductRepository;
import com.example.classicmodlesslaes.service.interfaces.ProductService;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updatedProduct(Product product) {
        return productRepository.updatedProduct(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findProductsByName(name);
    }

    @Override
    public List<Product> findProductsBelowStock(int stockLevel) {
        return productRepository.findProductsBelowStock(stockLevel);
    }

    @Override
    public List<Product> findProductsByVendor(String vendor) {
        return productRepository.findProductsByVendor(vendor);
    }

    @Override
    public List<Product> findProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findProductsInPriceRange(minPrice,maxPrice);
    }
}
