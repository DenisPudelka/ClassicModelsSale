package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.ProductLine;

public interface ProductLineRepository {

    // Basic crud
    ProductLine getProductLineById(String id);
    void saveProductLine(ProductLine productLine);
    void deleteProductLine(String id);
    void updateProductLine(ProductLine productLine);

    // Other
}
