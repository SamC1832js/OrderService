package com.example.rothurtech.orderservice.Repository;

import com.example.rothurtech.orderservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    void deleteByName(String name);
}
