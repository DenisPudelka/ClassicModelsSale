package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.ProductMapper;
import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.dto.product.ProductDetailDTO;
import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductBasicDTO>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductBasicDTO> productBasicDTOS = products.stream()
                .map(ProductMapper::toProductBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productBasicDTOS);
    }

    @GetMapping("/products/{productCode}")
    public ResponseEntity<ProductDetailDTO> getProductByProductCode(@PathVariable String productCode){
        Product product = productService.findProductById(productCode);
        ProductDetailDTO productDetailDTO = ProductMapper.toProductDetailDTO(product);
        return ResponseEntity.ok(productDetailDTO);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductBasicDTO> createProduct(@RequestBody Product product){
        return null;
    }

    @PutMapping("/products/{productCode}")
    public ResponseEntity<ProductDetailDTO> updateProduct(@PathVariable String productCode, @RequestBody Product product){
        return null;
    }

    @DeleteMapping("/products/{productCode}")
    public ResponseEntity<ProductBasicDTO> deleteProduct(@PathVariable String productCode){
        return null;
    }
}
