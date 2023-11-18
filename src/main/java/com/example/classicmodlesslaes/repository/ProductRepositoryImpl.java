package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.repository.interfaces.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findProductById(String id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product saveProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public Product updatedProduct(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public boolean deleteProduct(String id) {
        Product product = entityManager.find(Product.class, id);
        if(product != null) {
            entityManager.remove(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findProductsByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.productName LIKE :name",
                Product.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Product> findProductsBelowStock(int stockLevel) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.quantityInStock < :stockLevel",
                Product.class);
        query.setParameter("stockLevel", stockLevel);
        return query.getResultList();
    }

    @Override
    public List<Product> findProductsByVendor(String vendor) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.productVendor = :vendor",
                Product.class);
        query.setParameter("vendor", vendor);
        return query.getResultList();
    }

    @Override
    public List<Product> findProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.buyPrice BETWEEN :minPrice AND :maxPrice",
                Product.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }
}
