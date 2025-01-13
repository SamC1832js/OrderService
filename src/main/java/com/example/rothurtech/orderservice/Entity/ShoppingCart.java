package com.example.rothurtech.orderservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data //bundles @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    //mark products embedded collection of elements that are not entities themselves.
    //separate table
    @ElementCollection
    @CollectionTable(name = "shopping_cart_products", joinColumns = @JoinColumn(name = "shopping_cart_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

    @DecimalMin(value = "0.0", message = "Total price must be at least 0.0")
    @DecimalMax(value = "999999999.99", message = "Total price exceeds the maximum allowed")
    @Column(name = "total_price")
    private Double totalPrice;
}
