package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.repository.interfaces.ProductLineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductLineRepositoryImpl implements ProductLineRepository {

    private EntityManager entityManager;

    @Autowired
    public ProductLineRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public ProductLine getProductLineById(String id) {
        ProductLine productLine = entityManager.find(ProductLine.class, id);
        return productLine;
    }

    @Override
    @Transactional
    public void saveProductLine(ProductLine productLine) {
        entityManager.persist(productLine);
    }

    @Override
    @Transactional
    public void deleteProductLine(String id) {
        ProductLine productLine = entityManager.find(ProductLine.class, id);
        entityManager.remove(productLine);
    }

    @Override
    @Transactional
    public void updateProductLine(ProductLine productLine) {
        entityManager.merge(productLine);
    }
}
