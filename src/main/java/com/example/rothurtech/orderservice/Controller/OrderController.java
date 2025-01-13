package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.DTO.OrderDTO;
import com.example.rothurtech.orderservice.Service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrder(@PathVariable Long userId) {
        return new ResponseEntity<>(orderServiceImpl.getAllOrders(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long userId) {
        OrderDTO order = orderServiceImpl.addOrder(userId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}