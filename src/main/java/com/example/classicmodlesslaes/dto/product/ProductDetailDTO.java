package com.example.classicmodlesslaes.dto.product;

import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
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

    public ProductDetailDTO toProductDetailDTO(Product product){
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductCode(product.getProductCode());
        dto.setProductName(product.getProductName());
        dto.setProductScale(product.getProductScale());
        dto.setProductVendor(product.getProductVendor());
        dto.setProductDescription(product.getProductDescription());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setMsrp(product.getMsrp());

        // Convert ProductLine to ProductLineBasicDTO
        ProductLineBasicDTO productLineDTO = toProductLineBasicDTO(product.getProductLine()); // Assuming you have the method toProductLineBasicDTO for ProductLine.
        dto.setProductLine(productLineDTO);

        // Convert list of OrderDetail to list of OrderDetailBasicDTO
        List<OrderDetailBasicDTO> orderDetailDTOs = product.getOrderDetails().stream()
                .map(this::toOrderDetailBasicDTO)  // Assuming you have the method toOrderDetailBasicDTO for OrderDetail.
                .collect(Collectors.toList());
        dto.setOrderDetails(orderDetailDTOs);

        return dto;
    }

}

