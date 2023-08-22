package com.example.classicmodlesslaes.dto.productline;

import com.example.classicmodlesslaes.dto.mappers.ProductMapper;
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
}

