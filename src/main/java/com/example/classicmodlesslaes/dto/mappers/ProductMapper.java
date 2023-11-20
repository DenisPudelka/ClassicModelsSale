package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.dto.product.ProductDetailDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import com.example.classicmodlesslaes.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductBasicDTO toProductBasicDTO(Product product){
        ProductBasicDTO dto = new ProductBasicDTO();
        dto.setProductCode(product.getProductCode());
        dto.setProductName(product.getProductName());
        dto.setProductScale(product.getProductScale());
        dto.setProductVendor(product.getProductVendor());
        dto.setProductDescription(product.getProductDescription());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setMsrp(product.getMsrp());
        return dto;
    }

    public static ProductDetailDTO toProductDetailDTO(Product product){
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductCode(product.getProductCode());
        dto.setProductName(product.getProductName());
        dto.setProductScale(product.getProductScale());
        dto.setProductVendor(product.getProductVendor());
        dto.setProductDescription(product.getProductDescription());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setMsrp(product.getMsrp());

        ProductLineBasicDTO productLineDTO = ProductLineMapper.toProductLineBasicDTO(product.getProductLine());
        dto.setProductLine(productLineDTO);

        List<OrderDetailBasicDTO> orderDetailDTOs = product.getOrderDetails().stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());

        return dto;
    }

    public static Product toProductEntity(ProductBasicDTO productDTO){
        if(productDTO == null){
            return null;
        }

        Product product = new Product();
        product.setProductCode(productDTO.getProductCode());
        product.setProductName(productDTO.getProductName());
        product.setProductScale(productDTO.getProductScale());
        product.setProductVendor(productDTO.getProductVendor());
        product.setProductDescription(productDTO.getProductDescription());
        product.setQuantityInStock(productDTO.getQuantityInStock());
        product.setBuyPrice(productDTO.getBuyPrice());
        product.setMsrp(productDTO.getMsrp());

        return product;
    }
}

