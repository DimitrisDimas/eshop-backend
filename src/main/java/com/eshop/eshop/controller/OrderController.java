package com.eshop.eshop.controller;

import com.eshop.eshop.dto.orderDto.OrderResponse;
import com.eshop.eshop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId){
        OrderResponse order = orderService.getOrderById(orderId);
        if(order!=null){
            return ResponseEntity.ok(order);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
