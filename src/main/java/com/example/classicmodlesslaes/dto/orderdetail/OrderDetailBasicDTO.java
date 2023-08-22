package com.example.classicmodlesslaes.dto.orderdetail;

import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailBasicDTO {
    private OrderDetailId id;
    private int quantityOrdered;
    private BigDecimal priceEach;
    private short orderLineNumber;

    public OrderDetailBasicDTO toOrderDetailBasicDTO(OrderDetail orderDetail){
        OrderDetailBasicDTO dto = new OrderDetailBasicDTO();
        dto.setId(orderDetail.getId());
        dto.setQuantityOrdered(orderDetail.getQuantityOrdered());
        dto.setPriceEach(orderDetail.getPriceEach());
        dto.setOrderLineNumber(orderDetail.getOrderLineNumber());
        return dto;
    }

}
