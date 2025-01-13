package com.example.rothurtech.orderservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data //bundles @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    //mark products embedded collection of elements that are not entities themselves.
    //separate table
    @ElementCollection
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime date;

    @DecimalMin(value = "0.0", message = "Total price must be at least 0.0")
    @DecimalMax(value = "999999999.99", message = "Total price exceeds the maximum allowed")
    @Column(name = "total_price")
    private Double totalPrice;

}

