package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.OrderDetailMapper;
import com.example.classicmodlesslaes.dto.mappers.OrderMapper;
import com.example.classicmodlesslaes.dto.order.OrderBasicDTO;
import com.example.classicmodlesslaes.dto.order.OrderDetailDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import com.example.classicmodlesslaes.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderBasicDTO>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        List<OrderBasicDTO> orderBasicDTOS = orders.stream()
                .map(OrderMapper::toOrderBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderBasicDTOS);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderById(@PathVariable int id){
        Order order = orderService.getOrderById(id);
        OrderDetailDTO orderDetailDTO = OrderMapper.toOrderDetailDTO(order);
        return ResponseEntity.ok(orderDetailDTO);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDetailDTO> createOrder(@RequestBody OrderBasicDTO orderDTO){
        Customer customer = customerService.getCustomerById(orderDTO.getCustomerNumber());
        Order order = OrderMapper.toOrderEntity(orderDTO);
        order.setCustomer(customer);
        Order createOrder = orderService.saveOrder(order);
        OrderDetailDTO orderDetailDTO = OrderMapper.toOrderDetailDTO(createOrder);
        return ResponseEntity.ok(orderDetailDTO);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDetailDTO> updateOrder(@PathVariable int id, @RequestBody Order order){
        return null;
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/by-date-range")
    public ResponseEntity<List<OrderBasicDTO>> findOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Order> orders = orderService.findOrdersByDateRange(startDate, endDate);
        List<OrderBasicDTO> orderBasicDTOS = orders.stream()
                .map(OrderMapper::toOrderBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderBasicDTOS);
    }

    @GetMapping("/orders/pending-shipment")
    public ResponseEntity<List<OrderBasicDTO>> findPendingShipmentOrders() {
        List<Order> orders = orderService.findPendingShipmentOrders();
        List<OrderBasicDTO> orderBasicDTOS = orders.stream()
                .map(OrderMapper::toOrderBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderBasicDTOS);
    }

    @GetMapping("/orders/{orderNumber}/details")
    public ResponseEntity<List<OrderDetailBasicDTO>> findOrderDetailsByOrder(@PathVariable int orderNumber) {
        List<OrderDetail> orderDetails = orderService.findOrderDetailsByOrder(orderNumber);
        List<OrderDetailBasicDTO> orderDetailBasicDTOS = orderDetails.stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailBasicDTOS);
    }
}
