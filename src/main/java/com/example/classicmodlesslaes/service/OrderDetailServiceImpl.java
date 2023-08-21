package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;
import com.example.classicmodlesslaes.repository.interfaces.OrderDetailRepository;
import com.example.classicmodlesslaes.service.interfaces.OrderDetailService;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository repository){
        this.orderDetailRepository = repository;
    }

    @Override
    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.addOrderDetail(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(OrderDetailId id) {
        return orderDetailRepository.getOrderDetailById(id);
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.updateOrderDetail(orderDetail);
    }

    @Override
    public void deleteOrderDetail(OrderDetailId id) {
        orderDetailRepository.deleteOrderDetail(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNumber(int orderNumber) {
        return orderDetailRepository.getOrderDetailsByOrderNumber(orderNumber);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByProductCode(String productCode) {
        return orderDetailRepository.getOrderDetailsByProductCode(productCode);
    }

    @Override
    public BigDecimal getTotalAmountForOrder(int orderNumber) {
        return orderDetailRepository.getTotalAmountForOrder(orderNumber);
    }

    @Override
    public List<OrderDetail> getOrderDetailsWithQuantityAbove(int quantity) {
        return orderDetailRepository.getOrderDetailsWithQuantityAbove(quantity);
    }
}
