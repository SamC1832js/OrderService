package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.Entity.Order;
import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Service.impl.OrderServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.ShoppingCartServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable Long userId) {
        return new ResponseEntity<>(orderServiceImpl.getAllOrders(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId) {
        Order order = orderServiceImpl.addOrder(userId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}