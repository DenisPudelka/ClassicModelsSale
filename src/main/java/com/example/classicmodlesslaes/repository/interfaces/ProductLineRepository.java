package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.ProductLine;

import java.util.List;

public interface ProductLineRepository {
    // CRUD Operations
    ProductLine getProductLineById(String id);
    ProductLine saveProductLine(ProductLine productLine);
    void deleteProductLine(String id);
    ProductLine updateProductLine(ProductLine productLine);
    List<ProductLine> getAllProductLines();

    // Specific Queries
    List<ProductLine> getProductLinesByDescription(String description);
    List<ProductLine> getProductLinesWithImages();
    List<ProductLine> getProductLineWithoutImages();
    void updateImageForProductLine(String productLineId, byte[] newImage);
    List<ProductLine> searchProductLinesByPartialName(String partialName);


}
