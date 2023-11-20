package com.example.classicmodlesslaes.dto.product;

import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private String productCode;
    private String productName;
    private ProductLineBasicDTO productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
    private List<OrderDetailBasicDTO> orderDetails;
}

