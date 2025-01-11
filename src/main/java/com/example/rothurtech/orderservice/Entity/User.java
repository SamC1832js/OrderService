package com.example.rothurtech.orderservice.Entity;

import jakarta.validation.constraints.Email;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email_id", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
}
