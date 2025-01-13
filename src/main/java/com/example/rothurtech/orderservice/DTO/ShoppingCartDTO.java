package com.example.rothurtech.orderservice.DTO;

import com.example.rothurtech.orderservice.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Long id;
    private Map<Product, Integer> products;
}
