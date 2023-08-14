package com.example.classicmodlesslaes;

import com.example.classicmodlesslaes.model.*;
import com.example.classicmodlesslaes.repository.interfaces.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class ClassicmodlesslaesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassicmodlesslaesApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ProductLineRepository productLineRepository,
                                               ProductRepository productRepository,
                                               OfficeRepository officeRepository,
                                               EmployeeRepository employeeRepository,
                                               PaymentRepository paymentRepository,
                                               OrderRepository orderRepository,
                                               CustomerRepository customerRepository,
                                               OrderDetailRepository orderDetailRepository){
        return runner -> {
            //testProductLine(productLineRepository); -- toto robi secko
            //testProduct(productRepository); -- toto robisecko
            //testOffice(officeRepository); -- toto robi secko
            //testOrder(orderRepository, customerRepository); -- toto robi secko
            //testEmployee(employeeRepository, officeRepository); -- toto robi secko
            //testPayment(paymentRepository, customerRepository); -- toto robi
            //testCustomer(customerRepository); -- toto robi secko
            testOrderDetail(orderDetailRepository, orderRepository, productRepository);
        };
    }

    private void testOrderDetail(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        // get by id
//        OrderDetailId orderDetailId = new OrderDetailId(10100,"S18_1749");
//        OrderDetail orderDetail = orderDetailRepository.getOrderDetailById(orderDetailId);
//        System.out.println(orderDetail);

        // create orderDetail
//        OrderDetailId orderDetailId = new OrderDetailId(10100,"S10_1678");
//        Order order = orderRepository.findOrderById(10100);
//        Product product = productRepository.findProductById("S10_1678");
//        OrderDetail orderDetail = new OrderDetail(orderDetailId, order, product, 10, new BigDecimal(500), (short) 10);
//        orderDetailRepository.addOrderDetail(orderDetail);

//        OrderDetailId orderDetailId = new OrderDetailId(10100,"S10_1678");
//        OrderDetail orderDetail = orderDetailRepository.getOrderDetailById(orderDetailId);
//        orderDetail.setPriceEach(new BigDecimal(999));
//        orderDetailRepository.updateOrderDetail(orderDetail);

        // delete
//        OrderDetailId orderDetailId = new OrderDetailId(10100,"S10_1678");
//        orderDetailRepository.deleteOrderDetail(orderDetailId);

//        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrderNumber(10100);
//        for(OrderDetail orderDetail : orderDetails){
//            System.out.println(orderDetail);
//        }
    }

}
