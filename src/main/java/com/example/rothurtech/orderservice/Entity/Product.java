package com.example.rothurtech.orderservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;


//No need DTO, product can be public
@Data //bundles @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @DecimalMin(value = "0.0", message = "Price must be at least 0.0")
    @DecimalMax(value = "999999999.99", message = "Price exceeds the maximum allowed")
    @Column(nullable = false)
    private Double price;
}

