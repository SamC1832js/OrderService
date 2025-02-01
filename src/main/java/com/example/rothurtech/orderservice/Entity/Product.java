package com.example.rothurtech.orderservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.util.Objects;


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

    private String Category;

    private String Brand;

    private String imgUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

