package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.ProductLine;

import java.util.List;
import java.util.Map;

public interface ProductLineRepository {
    // CRUD Operations
    ProductLine getProductLineById(String id);
    ProductLine saveProductLine(ProductLine productLine);
    boolean deleteProductLine(String id);
    ProductLine updateProductLine(ProductLine productLine);
    List<ProductLine> getAllProductLines();

    // Specific Queries
    List<ProductLine> getProductLinesByDescription(String description);
    List<ProductLine> getProductLinesWithImages();
    List<ProductLine> getProductLineWithoutImages();
    void updateImageForProductLine(String productLineId, byte[] newImage);
    List<ProductLine> searchProductLinesByPartialName(String partialName);
    void updateProductDescriptionsForProductLine(ProductLine productLine, String newDescription);

    // Extensions Beyond The Current Table
    Map<String, Long> countProductsForAllProductLine();
    List<ProductLine> findProductLinesWithProductsBelowStock(int threshold);
    List<ProductLine> findProductLinesByVendor(String vendorName);
}
