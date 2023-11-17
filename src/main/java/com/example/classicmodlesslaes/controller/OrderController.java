package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.OrderMapper;
import com.example.classicmodlesslaes.dto.order.OrderBasicDTO;
import com.example.classicmodlesslaes.dto.order.OrderDetailDTO;
import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class OrderController {

    @Autowired
    private OrderService orderService;

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
    public ResponseEntity<OrderDetailDTO> createOrder(@RequestBody Order order){
        return null;
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDetailDTO> updateOrder(@PathVariable int id, @RequestBody Order order){
        return null;
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<OrderDetailDTO> deleteOrder(@PathVariable int id){
        Order order = orderService.getOrderById(id);
        orderService.deleteOrder(id);
        OrderDetailDTO orderDetailDTO = OrderMapper.toOrderDetailDTO(order);
        return ResponseEntity.ok(orderDetailDTO);
    }
}
