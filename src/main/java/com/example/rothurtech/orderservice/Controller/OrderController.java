package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.DTO.OrderDTO;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Service.impl.OrderServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl, UserServiceImpl userServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    private User getCurrentUser(HttpServletRequest request) {
        // Get user email from JWT token in SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userServiceImpl.getUserByEmailAuth(email);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getOrder(HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        return new ResponseEntity<>(orderServiceImpl.getAllOrders(currentUser.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(HttpServletRequest request, @PathVariable("id") Long id) {
        User currentUser = getCurrentUser(request);
        return new ResponseEntity<>(orderServiceImpl.getOrderById(currentUser.getId(), id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        OrderDTO order = orderServiceImpl.addOrder(currentUser.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}