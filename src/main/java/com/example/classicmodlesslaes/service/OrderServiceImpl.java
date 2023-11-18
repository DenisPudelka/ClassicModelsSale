package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.repository.interfaces.OrderRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.OrderService;
import com.example.classicmodlesslaes.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAllOrders();
            if(orders == null || orders.isEmpty()){
                throw new EntityNotFoundException("No orders found");
            }
            return orders;
        }catch (Exception e){
            throw  new DataAccessException("Error retrieving all orders.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(int id) {
        try {
            return orderRepository.findOrderById(id);
        }catch (Exception e){
            throw new DataAccessException("Error retrieving order.", e);
        }
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        try {
            return orderRepository.saveOrder(order);
        }catch (Exception e){
            throw new DataAccessException("Error saving order.", e);
        }
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        if(!existingOrder(order.getOrderNumber())){
            throw new EntityNotFoundException("Cannot update. Order with ID:" + order.getOrderNumber() + " not found");
        }
        try {
            return orderRepository.updateOrder(order);
        }catch (Exception e){
            throw new DataAccessException("Error updating order.", e);
        }
    }

    @Override
    @Transactional
    public void deleteOrder(int orderNumber) {
        if(!existingOrder(orderNumber)){
            throw new EntityNotFoundException("Cannot delete. Order with ID:" + orderNumber + " not found");
        }
        boolean wasDeleted = orderRepository.deleteOrder(orderNumber);
        if(!wasDeleted){
            throw new EntityNotFoundException("Order with ID: " + orderNumber + " not found and could not be deleted.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findOrdersByCustomer(int customerNumber) {
        try {
            List<Order> orders = orderRepository.findOrdersByCustomer(customerNumber);
            if(orders.isEmpty()){
                throw new EntityNotFoundException("No orders found for customer number: " + customerNumber);
            }
            return orders;
        }catch (Exception e){
            throw new ServiceException("Error fetching orders by customer number: " + customerNumber, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            return orderRepository.findOrdersByDateRange(startDate, endDate);
        }catch (Exception e){
            throw new ServiceException("Error fetching orders between dates: " + startDate + " and " + endDate, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> findOrderDetailsByOrder(int orderNumber) {
        try {
            List<OrderDetail> orderDetails = orderRepository.findOrderDetailsByOrder(orderNumber);
            if(orderDetails.isEmpty()){
                throw new EntityNotFoundException("No order details found for order number: " + orderNumber);
            }
            return orderDetails;
        }catch (Exception e){
            throw new ServiceException("Error fetching order details for order number: " + orderNumber, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findPendingShipmentOrders() {
        try {
            return orderRepository.findPendingShipmentOrders();
        }catch (Exception e){
            throw new ServiceException("Error fetching pending shipment orders.", e);
        }
    }

    private boolean existingOrder(int id){
        return orderRepository.findOrderById(id) != null;
    }
}
