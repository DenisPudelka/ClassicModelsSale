package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.order.OrderBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailDetailDTO;
import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.model.OrderDetail;

public class OrderDetailMapper {

    public static OrderDetailBasicDTO toOrderDetailBasicDTO(OrderDetail orderDetail){
        OrderDetailBasicDTO dto = new OrderDetailBasicDTO();
        dto.setOrderNumber(orderDetail.getId().getOrderNumber());
        dto.setProductCode(orderDetail.getId().getProductCode());
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

    public static OrderDetail toOrderDetailEntity (OrderDetailBasicDTO orderDetailDTO){
        if(orderDetailDTO == null){
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantityOrdered(orderDetailDTO.getQuantityOrdered());
        orderDetail.setPriceEach(orderDetailDTO.getPriceEach());
        orderDetail.setOrderLineNumber(orderDetailDTO.getOrderLineNumber());

        return orderDetail;
    }
}
