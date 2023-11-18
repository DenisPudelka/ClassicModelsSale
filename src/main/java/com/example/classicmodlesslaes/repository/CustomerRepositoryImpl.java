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
public class CustomerRepositoryImpl implements CustomerRepository {

    private EntityManager entityManager;

    @Autowired
    public CustomerRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        Customer customer = getCustomerById(id);
        if(customer != null) {
            entityManager.remove(customer);
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getCustomersByFirstName(String firstName) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.contactFirstName LIKE :firstName",
                Customer.class
        );

        query.setParameter("firstName", "%" + firstName + "%");

        return query.getResultList();
    }


    @Override
    public List<Customer> getCustomersByLastName(String lastName) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.contactLastName LIKE :lastName",
                Customer.class
        );

        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomersByCompanyName(String companyName) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.customerName = :companyName", Customer.class
        );
        query.setParameter("companyName", companyName);
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomersByCityName(String city) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.city = :cityName", Customer.class
        );
        query.setParameter("cityName", city);
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomersByCountry(String country) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.country = :country",
                Customer.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomersWithCreditLimitBeyond(BigDecimal limit) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.creditLimit > :limit",
                Customer.class);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomersWithoutSalesRep() {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.salesRep IS NULL",
                Customer.class);
        return query.getResultList();
    }
}
