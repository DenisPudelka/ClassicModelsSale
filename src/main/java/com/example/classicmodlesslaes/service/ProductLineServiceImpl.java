package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.repository.interfaces.ProductLineRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public ProductLine getProductLineById(String id) {
        ProductLine productLine = productLineRepository.getProductLineById(id);
        if(productLine == null){
            throw new EntityNotFoundException("ProductLine with ID: " + id + " not found.");
        }
        return productLine;
    }

    @Override
    @Transactional
    public ProductLine saveProductLine(ProductLine productLine) {
        try {
            return productLineRepository.saveProductLine(productLine);
        } catch (Exception e) {
            throw new DataAccessException("Error saving product line.", e);
        }
    }

    @Override
    @Transactional
    public void deleteProductLine(String id) {
        if(!productLineExists(id)) {
            throw new EntityNotFoundException("Cannot delete. ProductLine with ID: " + id + " not found.");
        }
        boolean wasDeleted = productLineRepository.deleteProductLine(id);
        if(!wasDeleted){
            throw new EntityNotFoundException("Product Line with ID: " + id + " not found and could not be deleted.");
        }
    }

    @Override
    @Transactional
    public ProductLine updateProductLine(ProductLine productLine) {
        if(!productLineExists(productLine.getProductLine())) {
            throw new EntityNotFoundException("Cannot update. ProductLine not found.");
        }
        try {
            return productLineRepository.updateProductLine(productLine);
        } catch (Exception e) {
            throw new DataAccessException("Error updating product line.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getAllProductLines() {
        List<ProductLine> productLines = productLineRepository.getAllProductLines();
        if(productLines == null || productLines.isEmpty()) {
            throw new EntityNotFoundException("No product lines found.");
        }
        return productLines;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLinesByDescription(String description) {
        List<ProductLine> productLines = productLineRepository.getProductLinesByDescription(description);
        if(productLines == null || productLines.isEmpty()){
            throw new EntityNotFoundException("No product line by description: " + description);
        }
        return productLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLinesWithImages() {
        List<ProductLine> productLines = productLineRepository.getProductLinesWithImages();
        if(productLines == null || productLines.isEmpty()){
            throw new EntityNotFoundException("No products lines with images");
        }
        return productLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLineWithoutImages() {
        List<ProductLine> productLines =  productLineRepository.getProductLineWithoutImages();
        if(productLines == null || productLines.isEmpty()){
            throw new EntityNotFoundException("No product line without images");
        }
        return productLines;
    }

    @Override
    @Transactional
    public void updateImageForProductLine(String productLineId, byte[] newImage) {
        if(!productLineExists(productLineId)){
            throw new EntityNotFoundException("Product line not found ID: " + productLineId);
        }
        try {
            productLineRepository.updateImageForProductLine(productLineId, newImage);
        }catch (Exception e){
            throw new DataAccessException("Cannot update product line image", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> searchProductLinesByPartialName(String partialName) {
        List<ProductLine> productLines = productLineRepository.searchProductLinesByPartialName(partialName);
        if(productLines == null || productLines.isEmpty()){
            throw new EntityNotFoundException("No product line with partial name: " + partialName);
        }
        return productLines;
    }

    @Override
    @Transactional
    public void updateProductDescriptionsForProductLine(ProductLine productLine, String newDescription) {
        if(!productLineExists(productLine.getProductLine())){
            throw new EntityNotFoundException("Product line not found ID: " + productLine.getProductLine());
        }
        try {
            productLineRepository.updateProductDescriptionsForProductLine(productLine, newDescription);
        }catch (Exception e){
            throw new DataAccessException("Cannot update product line description", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> countProductsForAllProductLine() {
        try {
            Map<String, Long> productCounts = productLineRepository.countProductsForAllProductLine();

            if (productCounts == null || productCounts.isEmpty()) {
                throw new EntityNotFoundException("No product lines found or they don't have any products.");
            }

            return productCounts;
        } catch (Exception e) {
            throw new DataAccessException("Error retrieving product counts for all product lines.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> findProductLinesWithProductsBelowStock(int threshold) {
        List<ProductLine> productLines = productLineRepository.findProductLinesWithProductsBelowStock(threshold);
        if(productLines == null || productLines.isEmpty()){
            throw new EntityNotFoundException("No product line below: " + threshold + " stock amount");
        }
        return productLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> findProductLinesByVendor(String vendorName) {
        List<ProductLine> productLines = productLineRepository.findProductLinesByVendor(vendorName);
        if(productLines == null || productLines.isEmpty()){
            throw new EntityNotFoundException("No product lines by vedor: " + vendorName);
        }
        return productLines;
    }

    private boolean productLineExists(String id) {
        return productLineRepository.getProductLineById(id) != null;
    }
}
