package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailRepository {
    // Besic CRUD
    OrderDetail addOrderDetail(OrderDetail orderDetail);
    OrderDetail getOrderDetailById(OrderDetailId id);
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(OrderDetailId id);

    // Specific queries
    List<OrderDetail> getOrderDetailsByOrderNumber(int orderNumber);
    List<OrderDetail> getOrderDetailsByProductCode(String productCode);
    BigDecimal getTotalAmountForOrder(int orderNumber);
    List<OrderDetail> getOrderDetailsWithQuantityAbove(int quantity);
}
