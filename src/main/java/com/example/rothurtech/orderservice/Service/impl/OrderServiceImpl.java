package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.Order;
import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Repository.OrderRepository;
import com.example.rothurtech.orderservice.Repository.ShoppingCartRepository;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository,UserRepository userRepository, ShoppingCartServiceImpl shoppingCartServiceImpl) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
    }

    public List<Order> getAllOrders(Long userId){
        Optional<List<Order>> op = Optional.ofNullable(orderRepository.findByUserId(userId));
        return op.orElse(new ArrayList<>());
    }


    public Order addOrder(Long userId){
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if(shoppingCart == null){
            throw new IllegalArgumentException("Shopping cart not found for user ID: " + userId);
        }
        User user = userRepository.findById(userId).orElse(null);
        Map<Product, Integer> products = new HashMap<>(shoppingCart.getProducts());
        Order order = new Order();
        order.setProducts(products);
        order.setUser(user);
        order.setDate(LocalDateTime.now());
        order.setTotalPrice(calculatePrice(products));
        shoppingCartServiceImpl.clearShoppingCart(userId);
        return orderRepository.save(order);
    }

    public Double calculatePrice(Map<Product, Integer> cart){
        double totalPrice = 0D;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Integer quantity = entry.getValue();
            double price = entry.getKey().getPrice() * quantity;
            totalPrice += price;
        }
        return totalPrice;
    }
}
