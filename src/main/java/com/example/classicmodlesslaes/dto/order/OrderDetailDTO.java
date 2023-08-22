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

    public OrderDetailDTO toOrderDetailDTO(Order order){
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrderDate(order.getOrderDate());
        dto.setRequiredDate(order.getRequiredDate());
        dto.setShippedDate(order.getShippedDate());
        dto.setStatus(order.getStatus());
        dto.setComments(order.getComments());

        // Convert Customer to CustomerBasicDTO
        CustomerBasicDTO customerDTO = new CustomerBasicDTO();
        customerDTO.setCustomerNumber(order.getCustomer().getCustomerNumber());
        // ... set other fields for customerDTO as necessary.
        dto.setCustomer(customerDTO);

        // Convert List<OrderDetail> to List<OrderDetailBasicDTO>
        List<OrderDetailBasicDTO> details = order.getOrderDetails().stream()
                .map(this::toOrderDetailBasicDTO) // This requires a method to convert OrderDetail to OrderDetailBasicDTO
                .collect(Collectors.toList());
        dto.setOrderDetails(details);

        return dto;
    }

}

