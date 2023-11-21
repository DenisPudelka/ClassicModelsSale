package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.OrderDetailMapper;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailDetailDTO;
import com.example.classicmodlesslaes.model.Order;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;
import com.example.classicmodlesslaes.model.Product;
import com.example.classicmodlesslaes.service.interfaces.OrderDetailService;
import com.example.classicmodlesslaes.service.interfaces.OrderService;
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
public class OrderDetailsController {

    private OrderDetailService orderDetailService;
    private ProductService productService;
    private OrderService orderService;

    @Autowired
    public OrderDetailsController(OrderDetailService orderDetailService, ProductService productService, OrderService orderService) {
        this.orderDetailService = orderDetailService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/orderdetails")
    public ResponseEntity<List<OrderDetailBasicDTO>> getAllOrderDetails(){
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        List<OrderDetailBasicDTO> orderDetailBasicDTOS = orderDetails.stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailBasicDTOS);
    }

    @GetMapping("/orderdetails/{orderNum}/{productCode}")
    public ResponseEntity<OrderDetailDetailDTO> getOrderDetailById(@PathVariable int orderNum, @PathVariable String productCode){
        OrderDetailId id = new OrderDetailId(orderNum, productCode);
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
        OrderDetailDetailDTO orderDetailDetailDTO = OrderDetailMapper.toOrderDetailDetailDTO(orderDetail);
        return ResponseEntity.ok(orderDetailDetailDTO);
    }

    @PostMapping("/orderdetails")
    public ResponseEntity<OrderDetailDetailDTO> createOrderDetail(@RequestBody OrderDetailBasicDTO orderDetailDTO){
        Order order = orderService.getOrderById(orderDetailDTO.getOrderNumber());
        Product product = productService.findProductById(orderDetailDTO.getProductCode());

        OrderDetailId orderDetailId = new OrderDetailId(orderDetailDTO.getOrderNumber(), orderDetailDTO.getProductCode());
        OrderDetail orderDetail = OrderDetailMapper.toOrderDetailEntity(orderDetailDTO);

        orderDetail.setId(orderDetailId);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        OrderDetail createOrderDetail = orderDetailService.addOrderDetail(orderDetail);
        OrderDetailDetailDTO orderDetailDetailDTO = OrderDetailMapper.toOrderDetailDetailDTO(createOrderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailDetailDTO);
    }

    // needs work
    @PutMapping("/orderdetails/{orderNum}/{productCode}")
    public ResponseEntity<OrderDetailDetailDTO> updateOrderDetail(@PathVariable int orderNum, @PathVariable String productCode, @RequestBody OrderDetail orderDetail){
        return null;
    }

    @DeleteMapping("/orderdetails/{orderNum}/{productCode}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int orderNum, @PathVariable String productCode){
        orderDetailService.deleteOrderDetail(new OrderDetailId(orderNum, productCode));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orderdetails/{orderNumber}/details")
    public ResponseEntity<List<OrderDetailBasicDTO>> getOrderDetailsByOrderNumber(@PathVariable int orderNumber) {
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderNumber(orderNumber);
        List<OrderDetailBasicDTO> orderDetailBasicDTOS = orderDetails.stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailBasicDTOS);
    }

    @GetMapping("/orderdetails/by-product/{productCode}")
    public ResponseEntity<List<OrderDetailBasicDTO>> getOrderDetailsByProductCode(@PathVariable String productCode) {
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByProductCode(productCode);
        List<OrderDetailBasicDTO> orderDetailBasicDTOS = orderDetails.stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailBasicDTOS);
    }

    @GetMapping("/orderdetails/{orderNumber}/total-amount")
    public ResponseEntity<BigDecimal> getTotalAmountForOrder(@PathVariable int orderNumber) {
        BigDecimal amount = orderDetailService.getTotalAmountForOrder(orderNumber);
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/orderdetails/with-quantity-above")
    public ResponseEntity<List<OrderDetailBasicDTO>> getOrderDetailsWithQuantityAbove(@RequestParam(required = false, defaultValue = "0") int quantity) {
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsWithQuantityAbove(quantity);
        List<OrderDetailBasicDTO> orderDetailBasicDTOS = orderDetails.stream()
                .map(OrderDetailMapper::toOrderDetailBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailBasicDTOS);
    }
}
