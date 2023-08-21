package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.repository.interfaces.ProductLineRepository;
import com.example.classicmodlesslaes.service.interfaces.ProductLineService;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    private final ProductLineRepository productLineRepository;

    @Autowired
    public ProductLineServiceImpl(ProductLineRepository productLineRepository){
        this.productLineRepository = productLineRepository;
    }

    @Override
    public ProductLine getProductLineById(String id) {
        return productLineRepository.getProductLineById(id);
    }

    @Override
    public ProductLine saveProductLine(ProductLine productLine) {
        return productLineRepository.saveProductLine(productLine);
    }

    @Override
    public void deleteProductLine(String id) {
        productLineRepository.deleteProductLine(id);
    }

    @Override
    public ProductLine updateProductLine(ProductLine productLine) {
        return productLineRepository.updateProductLine(productLine);
    }

    @Override
    public List<ProductLine> getAllProductLines() {
        return productLineRepository.getAllProductLines();
    }

    @Override
    public List<ProductLine> getProductLinesByDescription(String description) {
        return productLineRepository.getProductLinesByDescription(description);
    }

    @Override
    public List<ProductLine> getProductLinesWithImages() {
        return productLineRepository.getProductLinesWithImages();
    }

    @Override
    public List<ProductLine> getProductLineWithoutImages() {
        return productLineRepository.getProductLineWithoutImages();
    }

    @Override
    public void updateImageForProductLine(String productLineId, byte[] newImage) {
        productLineRepository.updateImageForProductLine(productLineId, newImage);
    }

    @Override
    public List<ProductLine> searchProductLinesByPartialName(String partialName) {
        return productLineRepository.searchProductLinesByPartialName(partialName);
    }

    @Override
    public void updateProductDescriptionsForProductLine(ProductLine productLine, String newDescription) {
        productLineRepository.updateProductDescriptionsForProductLine(productLine, newDescription);
    }

    @Override
    public Map<String, Long> countProductsForAllProductLine() {
        return productLineRepository.countProductsForAllProductLine();
    }

    @Override
    public List<ProductLine> findProductLinesWithProductsBelowStock(int threshold) {
        return productLineRepository.findProductLinesWithProductsBelowStock(threshold);
    }

    @Override
    public List<ProductLine> findProductLinesByVendor(String vendorName) {
        return productLineRepository.findProductLinesByVendor(vendorName);
    }
}
