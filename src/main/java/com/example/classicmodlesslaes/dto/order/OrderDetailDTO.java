package com.example.classicmodlesslaes.dto.order;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private OrderStatus status;
    private String comments;
    private CustomerBasicDTO customer; // a lightweight DTO representing the customer
    private List<OrderDetailBasicDTO> orderDetails; // a lightweight DTO representing each order detail
}

