package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderStatus;
import com.example.classicmodlesslaes.repository.interfaces.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManager entityManager;

    @Autowired
    public OrderRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Order findOrderById(int orderNumber) {
        return entityManager.find(Order.class, orderNumber);
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        // If customer is detached, this will return a managed version of it
        Customer managedCustomer = entityManager.merge(order.getCustomer());
        // Set the managed customer back to the order
        order.setCustomer(managedCustomer);
        // Now persist the order
        entityManager.persist(order);
        return order;
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        return entityManager.merge(order);
    }

    @Override
    @Transactional
    public void deleteOrder(int orderNumber) {
        Order order = findOrderById(orderNumber);
        entityManager.remove(order);
    }

    @Override
    public List<Order> findAllOrders() {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        return query.getResultList();
    }

    @Override
    public List<Order> findOrdersByCustomer(int customerNumber) {
        TypedQuery<Order> query = entityManager.createQuery(
                "SELECT o FROM Order o WHERE o.customer.customerNumber = :customerNumber",
                Order.class);
        query.setParameter("customerNumber", customerNumber);
        return query.getResultList();
    }

    @Override
    public List<Order> findOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        TypedQuery<Order> query = entityManager.createQuery(
                "SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate",
                Order.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> findOrderDetailsByOrder(int orderNumber) {
        TypedQuery<OrderDetail> query = entityManager.createQuery(
                "SELECT od FROM OrderDetail od WHERE od.id.orderNumber = :orderNumber",
                OrderDetail.class);
        query.setParameter("orderNumber", orderNumber);
        return query.getResultList();
    }

    @Override
    public List<Order> findPendingShipmentOrders() {
        TypedQuery<Order> query = entityManager.createQuery(
                "SELECT o FROM Order o WHERE o.shippedDate IS NULL",
                Order.class);
        return query.getResultList();
    }

}
