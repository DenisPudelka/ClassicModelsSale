package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.order.OrderBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailDetailDTO;
import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.model.OrderDetail;

public class OrderDetailMapper {

    public static OrderDetailBasicDTO toOrderDetailBasicDTO(OrderDetail orderDetail){
        OrderDetailBasicDTO dto = new OrderDetailBasicDTO();
        dto.setId(orderDetail.getId());
        dto.setQuantityOrdered(orderDetail.getQuantityOrdered());
        dto.setPriceEach(orderDetail.getPriceEach());
        dto.setOrderLineNumber(orderDetail.getOrderLineNumber());
        return dto;
    }

    public static OrderDetailDetailDTO toOrderDetailDetailDTO(OrderDetail orderDetail){
        OrderDetailDetailDTO dto = new OrderDetailDetailDTO();
        dto.setId(orderDetail.getId());
        dto.setQuantityOrdered(orderDetail.getQuantityOrdered());
        dto.setPriceEach(orderDetail.getPriceEach());
        dto.setOrderLineNumber(orderDetail.getOrderLineNumber());

        OrderBasicDTO orderDTO = OrderMapper.toOrderBasicDTO(orderDetail.getOrder());
        dto.setOrder(orderDTO);

        ProductBasicDTO productDTO = ProductMapper.toProductBasicDTO(orderDetail.getProduct());
        dto.setProduct(productDTO);

        return dto;
    }
}
