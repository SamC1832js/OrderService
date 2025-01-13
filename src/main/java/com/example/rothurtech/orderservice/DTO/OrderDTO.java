package com.example.rothurtech.orderservice.DTO;

import com.example.rothurtech.orderservice.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Map<Product, Integer> products;
    private LocalDateTime date;
    private Double totalPrice;
}
