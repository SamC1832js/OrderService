package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByEmail(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user == null || !user.getPassword().equals(password)){
            throw new IllegalArgumentException("Credential are incorrect");
        }else if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
    public User addUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
