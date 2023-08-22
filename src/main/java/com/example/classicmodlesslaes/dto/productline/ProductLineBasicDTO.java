package com.example.classicmodlesslaes.dto.productline;

import com.example.classicmodlesslaes.model.ProductLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineBasicDTO {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;

    public ProductLineBasicDTO toProductLineBasicDTO(ProductLine productLine){
        ProductLineBasicDTO dto = new ProductLineBasicDTO();
        dto.setProductLine(productLine.getProductLine());
        dto.setTextDescription(productLine.getTextDescription());
        dto.setHtmlDescription(productLine.getHtmlDescription());
        dto.setImage(productLine.getImage());
        return dto;
    }

}

