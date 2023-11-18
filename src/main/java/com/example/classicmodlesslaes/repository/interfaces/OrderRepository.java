package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderStatus;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    // Basic CRUD
    Order findOrderById(int orderNumber);
    Order saveOrder(Order order);
    Order updateOrder(Order order);
    boolean deleteOrder(int orderNumber);
    List<Order> findAllOrders();

    // Specific queries
    List<Order> findOrdersByCustomer(int customerNumber);
    List<Order> findOrdersByDateRange(LocalDate startDate, LocalDate endDate);
    List<OrderDetail> findOrderDetailsByOrder(int orderNumber);
    List<Order> findPendingShipmentOrders();

}
