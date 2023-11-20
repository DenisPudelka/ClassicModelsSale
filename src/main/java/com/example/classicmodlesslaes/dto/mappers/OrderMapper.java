package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.order.OrderBasicDTO;
import com.example.classicmodlesslaes.dto.order.OrderDetailDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderBasicDTO toOrderBasicDTO(Order order){
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

    public static OrderDetailDTO toOrderDetailDTO(Order order){
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrderDate(order.getOrderDate());
        dto.setRequiredDate(order.getRequiredDate());
        dto.setShippedDate(order.getShippedDate());
        dto.setStatus(order.getStatus());
        dto.setComments(order.getComments());

        dto.setCustomer(CustomerMapper.toCustomerBasicDTO(order.getCustomer()));

        List<OrderDetailBasicDTO> details = order.getOrderDetails().stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());
        dto.setOrderDetails(details);

        return dto;
    }

    public static Order toOrderEntity(OrderBasicDTO orderDTO){
        if(orderDTO == null){
            return null;
        }

        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());
        order.setRequiredDate(orderDTO.getRequiredDate());
        order.setShippedDate(orderDTO.getShippedDate());
        order.setStatus(orderDTO.getStatus());
        order.setComments(orderDTO.getComments());

        return order;
    }

}
