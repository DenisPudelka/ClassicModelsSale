package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;
import com.example.classicmodlesslaes.repository.interfaces.OrderDetailRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    private EntityManager entityManager;

    @Autowired
    public OrderDetailRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        entityManager.persist(orderDetail);
        return orderDetail;
    }

    @Override
    public OrderDetail getOrderDetailById(OrderDetailId id) {
        return entityManager.find(OrderDetail.class, id);
    }

    @Override
    @Transactional
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return entityManager.merge(orderDetail);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(OrderDetailId id) {
        OrderDetail orderDetail = getOrderDetailById(id);
        if (orderDetail != null) {
            entityManager.remove(orderDetail);
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNumber(int orderNumber) {
        TypedQuery<OrderDetail> query = entityManager.createQuery(
                "SELECT od FROM OrderDetail od WHERE od.id.orderNumber = :orderNumber",
                OrderDetail.class);
        query.setParameter("orderNumber", orderNumber);
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> getOrderDetailsByProductCode(String productCode) {
        TypedQuery<OrderDetail> query = entityManager.createQuery(
                "SELECT od FROM OrderDetail od WHERE od.id.productCode = :productCode",
                OrderDetail.class);
        query.setParameter("productCode", productCode);
        return query.getResultList();
    }

    @Override
    public BigDecimal getTotalAmountForOrder(int orderNumber) {
        TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT SUM(od.priceEach * od.quantityOrdered) FROM OrderDetail od WHERE od.id.orderNumber = :orderNumber",
                BigDecimal.class);
        query.setParameter("orderNumber", orderNumber);
        return query.getSingleResult();
    }

    @Override
    public List<OrderDetail> getOrderDetailsWithQuantityAbove(int quantity) {
        TypedQuery<OrderDetail> query = entityManager.createQuery(
                "SELECT od FROM OrderDetail od WHERE od.quantityOrdered > :quantity",
                OrderDetail.class);
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }

}
