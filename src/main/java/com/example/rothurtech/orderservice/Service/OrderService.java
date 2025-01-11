package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.Entity.Order;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders(User user);
    Order addOrder(ShoppingCart shoppingCart);
    void deleteOrderById(Long id);
}
