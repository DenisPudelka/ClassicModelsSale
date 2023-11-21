package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.ProductMapper;
import com.example.classicmodlesslaes.dto.product.ProductBasicDTO;
import com.example.classicmodlesslaes.dto.product.ProductDetailDTO;
import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.service.interfaces.ProductLineService;
import com.example.classicmodlesslaes.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class ProductController {


    private ProductService productService;
    private ProductLineService productLineService;

    @Autowired
    public ProductController(ProductService productService, ProductLineService productLineService) {
        this.productService = productService;
        this.productLineService = productLineService;
    }

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
    public ResponseEntity<ProductDetailDTO> createProduct(@RequestBody ProductBasicDTO productDTO){
        ProductLine productLine = productLineService.getProductLineById(productDTO.getProductLine());
        Product product = ProductMapper.toProductEntity(productDTO);
        product.setProductLine(productLine);
        Product createProduct = productService.saveProduct(product);
        ProductDetailDTO productDetailDTO = ProductMapper.toProductDetailDTO(createProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDetailDTO);
    }

    // needs work
    @PutMapping("/products/{productCode}")
    public ResponseEntity<ProductDetailDTO> updateProduct(@PathVariable String productCode, @RequestBody Product product){
        return null;
    }

    @DeleteMapping("/products/{productCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode){
        productService.deleteProduct(productCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/byName/{name}")
    public ResponseEntity<List<ProductBasicDTO>> findProductsByName(@PathVariable String name){
        List<Product> products = productService.findProductsByName(name);
        List<ProductBasicDTO> productBasicDTOS = products.stream()
                .map(ProductMapper::toProductBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productBasicDTOS);
    }

    @GetMapping("/products/below-stock")
    public ResponseEntity<List<ProductBasicDTO>> findProductsBelowStock(@RequestParam(required = true) int stockLevel){
        List<Product> products = productService.findProductsBelowStock(stockLevel);
        List<ProductBasicDTO> productBasicDTOS = products.stream()
                .map(ProductMapper::toProductBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productBasicDTOS);
    }

    @GetMapping("/products/by-vendor")
    public ResponseEntity<List<ProductBasicDTO>> findProductsByVendor(@RequestParam(required = true) String vendorName){
        List<Product> products = productService.findProductsByVendor(vendorName);
        List<ProductBasicDTO> productBasicDTOS = products.stream()
                .map(ProductMapper::toProductBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productBasicDTOS);
    }

    @GetMapping("/products/in-price-range")
    public ResponseEntity<List<ProductBasicDTO>> findProductsInPriceRange(@RequestParam(required = false, defaultValue = "0")BigDecimal minPrice, @RequestParam(required = false, defaultValue = "1000000") BigDecimal maxPrice){
        List<Product> products = productService.findProductsInPriceRange(minPrice,maxPrice);
        List<ProductBasicDTO> productBasicDTOS = products.stream()
                .map(ProductMapper::toProductBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productBasicDTOS);
    }
}
