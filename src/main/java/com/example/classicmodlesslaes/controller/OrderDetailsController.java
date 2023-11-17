package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.OrderDetailMapper;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailBasicDTO;
import com.example.classicmodlesslaes.dto.orderdetail.OrderDetailDetailDTO;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.model.OrderDetailId;
import com.example.classicmodlesslaes.service.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class OrderDetailsController {

    @Autowired
    private OrderDetailService orderDetailService;

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
    public ResponseEntity<OrderDetailDetailDTO> createOrderDetail(@RequestBody OrderDetail orderDetail){
        return null;
    }

    @PutMapping("/orderdetails/{orderNum}/{productCode}")
    public ResponseEntity<OrderDetailDetailDTO> updateOrderDetail(@PathVariable int orderNum, @PathVariable String productCode, @RequestBody OrderDetail orderDetail){
        return null;
    }

    @DeleteMapping("/orderdetails/{orderNum}/{productCode}")
    public ResponseEntity<OrderDetailDetailDTO> deleteOrderDetail(@PathVariable int orderNum, @PathVariable String productCode){
        return null;
    }
}
