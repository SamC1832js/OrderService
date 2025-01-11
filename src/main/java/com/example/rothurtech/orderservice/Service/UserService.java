package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.Entity.User;

public interface UserService {
    public User getUserById(Long id);
    public User getUserByEmail(String email);
    public User addUser(User user);
    public void deleteUser(Long id);
}
