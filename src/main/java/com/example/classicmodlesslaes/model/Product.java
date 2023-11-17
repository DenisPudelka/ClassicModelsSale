package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"orderDetails"})
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "productcode", nullable = false)
    @EqualsAndHashCode.Include
    private String productCode;

    @Column(name = "productname", nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.EAGER) // FetchType.LAZY
    @JoinColumn(name = "productline")
    private ProductLine productLine;

    @Column(name = "productscale", nullable = true)
    private String productScale;

    @Column(name = "productvendor", nullable = true)
    private String productVendor;

    @Column(name = "productdescription", columnDefinition = "TEXT", nullable = true)
    private String productDescription;

    @Column(name = "quantityinstock", nullable = true)
    private int quantityInStock;

    @Column(name = "buyprice", nullable = true)
    private BigDecimal buyPrice;

    @Column(name = "msrp", nullable = true)
    private BigDecimal msrp;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();


    public Product(String productCode, String productName, String productScale, String productVendor, String productDescription, int quantityInStock, BigDecimal buyPrice, BigDecimal msrp) {
        this.productCode = productCode;
        this.productName = productName;
        this.productScale = productScale;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.msrp = msrp;
    }

    // Utility Methods

    public void setProductLine(ProductLine productLine){
        if(this.productLine != null){
            this.productLine.getProducts().remove(this);
        }
        this.productLine = productLine;
        if(productLine != null){
            productLine.getProducts().add(this);
        }
    }

    public void removeFromProductLine(){
        if(this.productLine != null){
            this.productLine.getProducts().remove(this);
        }
        this.productLine = null;
    }

    public void addOrderDetail(Order order, int quantity, BigDecimal price, short lineNumber) {
        OrderDetail detail = new OrderDetail();
        detail.setOrder(order);
        detail.setProduct(this);
        detail.setQuantityOrdered(quantity);
        detail.setPriceEach(price);
        detail.setOrderLineNumber(lineNumber);
        orderDetails.add(detail);
    }

    public void removeOrderDetail(OrderDetail detail) {
        orderDetails.remove(detail);
        detail.setProduct(null);
        detail.setOrder(null);
    }
}
