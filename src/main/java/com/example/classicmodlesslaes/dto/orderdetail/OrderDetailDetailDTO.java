package com.example.classicmodlesslaes.dto.orderdetail;

import com.example.classicmodlesslaes.dto.order.OrderBasicDTO;
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
public class OrderDetailDetailDTO {
    private OrderDetailId id;
    private int quantityOrdered;
    private BigDecimal priceEach;
    private short orderLineNumber;
    private OrderBasicDTO order;
    private ProductBasicDTO product;  // Assuming there's a ProductBasicDTO class.

    public OrderDetailDetailDTO toOrderDetailDetailDTO(OrderDetail orderDetail){
        OrderDetailDetailDTO dto = new OrderDetailDetailDTO();
        dto.setId(orderDetail.getId());
        dto.setQuantityOrdered(orderDetail.getQuantityOrdered());
        dto.setPriceEach(orderDetail.getPriceEach());
        dto.setOrderLineNumber(orderDetail.getOrderLineNumber());

        // Convert Order to OrderBasicDTO
        OrderBasicDTO orderDTO = toOrderBasicDTO(orderDetail.getOrder()); // Assuming you have the method toOrderBasicDTO for Order.
        dto.setOrder(orderDTO);

        // Convert Product to ProductBasicDTO
        ProductBasicDTO productDTO = toProductBasicDTO(orderDetail.getProduct()); // You'll need to create this method.
        dto.setProduct(productDTO);

        return dto;
    }

}

