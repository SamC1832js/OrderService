package com.example.rothurtech.orderservice.Repository;

import com.example.rothurtech.orderservice.Entity.Order;
import com.example.rothurtech.orderservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    List<Order> findByUserId(Long userId);
}
