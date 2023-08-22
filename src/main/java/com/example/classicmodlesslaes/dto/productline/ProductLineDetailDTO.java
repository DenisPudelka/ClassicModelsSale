package com.example.classicmodlesslaes.dto.productline;

import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.model.ProductLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineDetailDTO {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;
    private List<ProductBasicDTO> products;  // Assuming you have a ProductBasicDTO class.

    public ProductLineDetailDTO toProductLineDetailDTO(ProductLine productLine){
        ProductLineDetailDTO dto = new ProductLineDetailDTO();
        dto.setProductLine(productLine.getProductLine());
        dto.setTextDescription(productLine.getTextDescription());
        dto.setHtmlDescription(productLine.getHtmlDescription());
        dto.setImage(productLine.getImage());

        // Convert list of Product to list of ProductBasicDTO
        List<ProductBasicDTO> productDTOs = productLine.getProducts().stream()
                .map(ProductBasicDTO::toProductBasicDTO)  // Assuming you have the method toProductBasicDTO for Product.
                .collect(Collectors.toList());
        dto.setProducts(productDTOs);

        return dto;
    }

}

