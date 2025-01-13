package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.DTO.UserDTO;
import com.example.rothurtech.orderservice.Entity.User;

public interface UserService {
    public UserDTO getUserById(Long id);
    public UserDTO getUserByEmail(String email);
    public UserDTO addUser(User user);
    public void deleteUser(Long id);
}
