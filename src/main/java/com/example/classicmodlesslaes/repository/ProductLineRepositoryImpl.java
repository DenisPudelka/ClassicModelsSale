package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.repository.interfaces.ProductLineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class ProductLineRepositoryImpl implements ProductLineRepository {

    private EntityManager entityManager;

    @Autowired
    public ProductLineRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductLine getProductLineById(String id) {
        return entityManager.find(ProductLine.class, id);
    }

    @Override
    @Transactional
    public ProductLine saveProductLine(ProductLine productLine) {
        entityManager.persist(productLine);
        return productLine;
    }

    @Override
    @Transactional
    public void deleteProductLine(String id) {
        ProductLine productLine = entityManager.find(ProductLine.class, id);
        if(productLine != null) {
            entityManager.remove(productLine);
        }
    }

    @Override
    @Transactional
    public ProductLine updateProductLine(ProductLine productLine) {
        return entityManager.merge(productLine);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getAllProductLines() {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl", ProductLine.class);
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLinesByDescription(String description) {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl WHERE pl.textDescription LIKE :keyword", ProductLine.class);
        query.setParameter("keyword", "%" + description + "%");
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLinesWithImages() {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl WHERE pl.image != null", ProductLine.class);
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLineWithoutImages() {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl WHERE pl.image = null", ProductLine.class);
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    @Transactional
    public void updateImageForProductLine(String productLineId, byte[] newImage) {
        ProductLine productLine = entityManager.find(ProductLine.class, productLineId);
        productLine.setImage(newImage);
        updateProductLine(productLine);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductLine> searchProductLinesByPartialName(String partialName) {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl WHERE pl.productLine LIKE :keyword", ProductLine.class);
        query.setParameter("keyword", "%" + partialName + "%");
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    @Transactional
    public void updateProductDescriptionsForProductLine(ProductLine productLine, String newDescription) {
        ProductLine managedProductLine = entityManager.merge(productLine);
        List<Product> products = managedProductLine.getProducts();
        for(Product product : products){
            product.setProductDescription(newDescription);
        }
    }

    @Override
    public Map<String, Long> countProductsForAllProductLine() {
        String jpqlQuery = "SELECT pl.productLine, COUNT(p) " +
                "FROM ProductLine pl " +
                "LEFT JOIN pl.products p " +
                "GROUP BY pl.productLine";

        List<Object[]> results = entityManager.createQuery(jpqlQuery).getResultList();

        Map<String, Long> productCounts = new HashMap<>();
        for(Object[] result : results){
            String productLine = (String) result[0];
            Long count = (Long) result[1];
            productCounts.put(productLine, count);
        }

        return productCounts;
    }

    @Override
    public List<ProductLine> findProductLinesWithProductsBelowStock(int threshold) {
        String jpql = "SELECT DISTINCT pl FROM ProductLine pl " +
                      "JOIN pl.products p " +
                      "WHERE p.quantityInStock < :threshold";

        TypedQuery<ProductLine> query = entityManager.createQuery(jpql, ProductLine.class);
        query.setParameter("threshold", threshold);
        return query.getResultList();
    }

    @Override
    public List<ProductLine> findProductLinesByVendor(String vendorName) {
        String jpql = "SELECT DISTINCT p.productLine " +
                "FROM Product p " +
                "WHERE p.productVendor = :vendorName";

        return entityManager.createQuery(jpql, ProductLine.class)
                .setParameter("vendorName", vendorName)
                .getResultList();
    }
}
