package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.repository.interfaces.ProductRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.ProductService;
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
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        try {
            return productRepository.getAllProducts();
        }catch (Exception e){
            throw new DataAccessException("Error fetching all products", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(String id) {
        Product product = productRepository.findProductById(id);
        if(product == null){
            throw new EntityNotFoundException("Product with ID: " + id + " not found.");
        }
        return product;
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        try {
            return productRepository.saveProduct(product);
        } catch (Exception e) {
            throw new DataAccessException("Error saving product.", e);
        }
    }

    @Override
    @Transactional
    public Product updatedProduct(Product product) {
        if(product == null || product.getProductCode() == null || findProductById(product.getProductCode()) == null) {
            throw new EntityNotFoundException("Cannot update. Product not found.");
        }
        try {
            return productRepository.updatedProduct(product);
        } catch (Exception e) {
            throw new DataAccessException("Error updating product.", e);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        if(findProductById(id) == null) {
            throw new EntityNotFoundException("Cannot delete. Product with ID: " + id + " not found.");
        }
        boolean wasDeleted = productRepository.deleteProduct(id);
        if(!wasDeleted){
            throw new EntityNotFoundException("Product with ID: " + id + " not found and could not be deleted.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsByName(String name) {
        List<Product> products = productRepository.findProductsByName(name);
        if(products == null || products.isEmpty()){
            throw new EntityNotFoundException("No products found with name: " + name);
        }
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsBelowStock(int stockLevel) {
        List<Product> products = productRepository.findProductsBelowStock(stockLevel);
        if(products == null || products.isEmpty()){
            throw new EntityNotFoundException("No products found below stock level: " + stockLevel);
        }
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsByVendor(String vendor) {
        List<Product> products = productRepository.findProductsByVendor(vendor);
        if(products == null || products.isEmpty()){
            throw new EntityNotFoundException("No products found for vendor: " + vendor);
        }
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productRepository.findProductsInPriceRange(minPrice, maxPrice);
        if(products == null || products.isEmpty()){
            throw new EntityNotFoundException("No products found in price range: " + minPrice + " - " + maxPrice);
        }
        return products;
    }
}
