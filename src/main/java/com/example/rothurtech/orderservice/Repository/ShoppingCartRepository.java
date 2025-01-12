package com.example.rothurtech.orderservice.Repository;

import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUserId(Long userId);
    // Spring interprets this as:
    // 1. "user" -> refers to the User entity relationship in ShoppingCart
    // 2. "Id" -> refers to the id field of the User entity
    // This effectively generates a SQL query like:
    // SELECT * FROM shopping_cart WHERE user_id = ?
}
