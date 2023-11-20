package com.example.classicmodlesslaes.dto.productline;

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
}

