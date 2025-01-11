package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.Entity.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    User addUser(User user);
    void deleteUser(Long id);
}
