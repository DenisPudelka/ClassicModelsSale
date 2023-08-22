package com.example.classicmodlesslaes.dto.product;

import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import com.example.classicmodlesslaes.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private String productCode;
    private String productName;
    private ProductLineBasicDTO productLine;  // Assuming there's a ProductLineBasicDTO class.
    private String productScale;
    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
    private List<OrderDetailBasicDTO> orderDetails;  // Assuming there's an OrderDetailBasicDTO class.
}

