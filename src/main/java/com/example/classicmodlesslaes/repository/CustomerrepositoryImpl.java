package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.repository.interfaces.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CustomerrepositoryImpl implements CustomerRepository {

    private EntityManager entityManager;

    @Autowired
    public CustomerrepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Customer getCustomerById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        return customer;
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        return entityManager.merge(customer);
    }
    @Override
    @Transactional
    public void deleteCustomer(int id) {
        Customer customer = getCustomerById(id);
        if(customer != null) {
            entityManager.remove(customer);
        }
    }

    @Override
    @Transactional
    public List<Customer> getCustomersByName(String name) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.customerName LIKE :name",
                Customer.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Customer> getCustomersByCountry(String country) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.country = :country",
                Customer.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Customer> getCustomersWithCreditLimitBeyond(BigDecimal limit) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.creditLimit > :limit",
                Customer.class);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Customer> getCustomersWithoutSalesRep() {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.salesRep IS NULL",
                Customer.class);
        return query.getResultList();
    }
}
