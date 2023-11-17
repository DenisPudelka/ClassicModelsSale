package com.example.classicmodlesslaes.service.interfaces;

import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderDetail;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    // Basic CRUD
    List<Order> getAllOrders();
    Order getOrderById(int id);
    Order saveOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(int orderNumber);

    // Specific Query
    List<Order> findOrdersByCustomer(int customerNumber);
    List<Order> findOrdersByDateRange(LocalDate startDate, LocalDate endDate);
    List<OrderDetail> findOrderDetailsByOrder(int orderNumber);
    List<Order> findPendingShipmentOrders();
}
