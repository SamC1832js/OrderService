package com.example.rothurtech.orderservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDTO {
    private String productName;
    private Integer quantity;
}
