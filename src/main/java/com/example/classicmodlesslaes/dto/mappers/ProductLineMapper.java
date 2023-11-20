package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineDetailDTO;
import com.example.classicmodlesslaes.model.ProductLine;

import java.util.List;
import java.util.stream.Collectors;

public class ProductLineMapper {

    public static ProductLineBasicDTO toProductLineBasicDTO(ProductLine productLine){
        ProductLineBasicDTO dto = new ProductLineBasicDTO();
        dto.setProductLine(productLine.getProductLine());
        dto.setTextDescription(productLine.getTextDescription());
        dto.setHtmlDescription(productLine.getHtmlDescription());
        dto.setImage(productLine.getImage());
        return dto;
    }

    public static ProductLineDetailDTO toProductLineDetailDTO(ProductLine productLine){
        ProductLineDetailDTO dto = new ProductLineDetailDTO();
        dto.setProductLine(productLine.getProductLine());
        dto.setTextDescription(productLine.getTextDescription());
        dto.setHtmlDescription(productLine.getHtmlDescription());
        dto.setImage(productLine.getImage());

        List<ProductBasicDTO> productDTOs = productLine.getProducts().stream()
                .map(ProductMapper::toProductBasicDTO)
                .collect(Collectors.toList());
        dto.setProducts(productDTOs);

        return dto;
    }

    public static ProductLine toProductLineEntity(ProductLineBasicDTO productLineDTO){
        if(productLineDTO == null){
            return null;
        }

        ProductLine productLine = new ProductLine();
        productLine.setProductLine(productLineDTO.getProductLine());
        productLine.setTextDescription(productLineDTO.getTextDescription());
        productLine.setHtmlDescription(productLineDTO.getHtmlDescription());
        productLine.setImage(productLineDTO.getImage());

        return productLine;
    }
}
