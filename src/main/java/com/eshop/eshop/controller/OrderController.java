package com.eshop.eshop.controller;

import com.eshop.eshop.dto.orderDto.OrderDto;
import com.eshop.eshop.dto.orderDto.OrderResponse;
import com.eshop.eshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<Integer> createOrder(@Valid @RequestBody OrderDto orderDto){
        Integer orderId = orderService.createOrder(orderDto);
        if(orderId!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
