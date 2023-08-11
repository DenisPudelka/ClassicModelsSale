package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.repository.interfaces.ProductLineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<ProductLine> getAllProductLines() {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl", ProductLine.class);
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    public List<ProductLine> getProductLinesByDescription(String description) {
        List<ProductLine> productLines = new ArrayList<>();
        TypedQuery<ProductLine> query = entityManager.createQuery("SELECT pl FROM ProductLine pl WHERE pl.textDescription LIKE :keyword", ProductLine.class);
        query.setParameter("keyword", "%" + description + "%");
        productLines = query.getResultList();
        return productLines;
    }

    @Override
    public List<ProductLine> getProductLinesWithImages() {
        return null;
    }
}
