package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.DTO.OrderDTO;
import com.example.rothurtech.orderservice.Entity.Order;
import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Mapper.OrderMapper;
import com.example.rothurtech.orderservice.Repository.OrderRepository;
import com.example.rothurtech.orderservice.Repository.ShoppingCartRepository;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository,UserRepository userRepository, ShoppingCartServiceImpl shoppingCartServiceImpl, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTO> getAllOrders(Long userId){
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }


    public OrderDTO addOrder(Long userId){
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if(shoppingCart == null){
            throw new IllegalArgumentException("Shopping cart not found for user ID: " + userId);
        }
        User user = userRepository.findById(userId).orElse(null);
        Map<Product, Integer> products = new HashMap<>(shoppingCart.getProducts());
        if(products.isEmpty()){
            throw new IllegalArgumentException("No product in the shopping cart, please add products to checkout " + userId);
        }
        Order order = new Order();
        order.setProducts(products);
        order.setUser(user);
        order.setDate(LocalDateTime.now());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        shoppingCartServiceImpl.clearShoppingCart(userId);
        return orderMapper.toOrderDTO(orderRepository.save(order));
    }

//    public Double calculatePrice(Map<Product, Integer> cart){
//        double totalPrice = 0D;
//        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
//            Integer quantity = entry.getValue();
//            double price = entry.getKey().getPrice() * quantity;
//            totalPrice += price;
//        }
//        if (totalPrice < 0 || totalPrice > Double.MAX_VALUE) {
//            throw new IllegalArgumentException("Calculated total price is invalid: " + totalPrice);
//        }
//        return totalPrice;
//    }
}
