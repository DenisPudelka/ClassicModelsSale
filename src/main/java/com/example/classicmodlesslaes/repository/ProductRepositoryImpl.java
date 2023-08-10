package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.repository.interfaces.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Product findProductById(String id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public void updatedProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }
}
