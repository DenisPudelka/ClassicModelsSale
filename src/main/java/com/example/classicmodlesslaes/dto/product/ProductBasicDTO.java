package com.example.classicmodlesslaes.dto.product;

import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBasicDTO {
    private String productCode;
    private String productName;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
    private String productLine;
}

