package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.Order;
import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    List<Order> getAllOrders(User user){
        return orderRepository.findByUser(user);
    }

    Order addOrder(ShoppingCart shoppingCart){
        Map<Product, Integer> cart = shoppingCart.getProducts();
        Order order = new Order();
        order.setProducts(cart);
        order.setDate(LocalDateTime.now());
        order.setTotalPrice(calculatePrice(cart));
        return orderRepository.save(order);
    }

    Double calculatePrice(Map<Product, Integer> cart){
        double totalPrice = 0D;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Integer quantity = entry.getValue();
            double price = entry.getKey().getPrice() * quantity;
            totalPrice += price;
        }
        return totalPrice;
    }
    void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }
}
