package com.example.classicmodlesslaes.dto.order;

import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBasicDTO {
    private int orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private OrderStatus status;
    private String comments;
    private int customerNumber;

    public OrderBasicDTO toOrderBasicDTO(Order order){
        OrderBasicDTO dto = new OrderBasicDTO();
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrderDate(order.getOrderDate());
        dto.setRequiredDate(order.getRequiredDate());
        dto.setShippedDate(order.getShippedDate());
        dto.setStatus(order.getStatus());
        dto.setComments(order.getComments());
        dto.setCustomerNumber(order.getCustomer().getCustomerNumber());
        return dto;
    }

}

