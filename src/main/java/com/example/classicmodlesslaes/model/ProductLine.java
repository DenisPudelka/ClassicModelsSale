package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "productlines")
public class ProductLine {

    @Id
    @Column(name = "productline", nullable = false)
    @EqualsAndHashCode.Include
    private String productLine;

    @Column(name = "textdescription", nullable = true)
    private String textDescription;

    @Lob
    @Column(name = "htmldescription", nullable = true)
    private String htmlDescription;

    @Lob
    @Column(name = "image", nullable = true)
    private byte[] image;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productLine", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public ProductLine(String productLine, String textDescription, String htmlDescription, byte[] image) {
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
        this.image = image;
    }

    public void addProduct(Product product){
        products.add(product);
        product.setProductLine(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.setProductLine(null);
    }
}
