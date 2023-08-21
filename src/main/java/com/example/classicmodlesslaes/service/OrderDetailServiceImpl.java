package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;
import com.example.classicmodlesslaes.repository.interfaces.OrderDetailRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        try {
            return orderDetailRepository.addOrderDetail(orderDetail);
        } catch (Exception e){
            throw new DataAccessException("Error adding order detail.", e);
        }
    }

    @Override
    public OrderDetail getOrderDetailById(OrderDetailId id) {
        OrderDetail orderDetail = orderDetailRepository.getOrderDetailById(id);
        if(orderDetail == null){
            throw new EntityNotFoundException("OrderDetail with ID: " + id + " not found.");
        }
        return orderDetail;
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        if(!orderDetailExist(orderDetail.getId())){
            throw new EntityNotFoundException("Cannot update. OrderDetail not found.");
        }
        try {
            return orderDetailRepository.updateOrderDetail(orderDetail);
        }catch (Exception e){
            throw new DataAccessException("Error updating order detail.", e);
        }
    }

    @Override
    public void deleteOrderDetail(OrderDetailId id) {
        if(!orderDetailExist(id)){
            throw new EntityNotFoundException("Cannot delete. OrderDetail with ID: " + id + " not found.");
        }
        orderDetailRepository.deleteOrderDetail(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNumber(int orderNumber) {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrderNumber(orderNumber);
        if(orderDetails == null || orderDetails.isEmpty()){
            throw new EntityNotFoundException("No order details found for order number: " + orderNumber);
        }
        return orderDetails;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByProductCode(String productCode) {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByProductCode(productCode);
        if(orderDetails == null || orderDetails.isEmpty()){
            throw new EntityNotFoundException("No order details found for product code: " + productCode);
        }
        return orderDetails;
    }


    @Override
    public BigDecimal getTotalAmountForOrder(int orderNumber) {
        BigDecimal totalAmount = orderDetailRepository.getTotalAmountForOrder(orderNumber);
        if(totalAmount == null){
            throw new EntityNotFoundException("Total amount not found for order number: " + orderNumber);
        }
        return totalAmount;
    }

    @Override
    public List<OrderDetail> getOrderDetailsWithQuantityAbove(int quantity) {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsWithQuantityAbove(quantity);
        if(orderDetails.isEmpty()){
            throw new EntityNotFoundException("No order details found with quantity above: " + quantity);
        }
        return orderDetails;
    }

    private boolean orderDetailExist(OrderDetailId id) {
        return orderDetailRepository.getOrderDetailById(id) != null;
    }
}
