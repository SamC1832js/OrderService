package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.DTO.UserDTO;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Mapper.UserMapper;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public UserDTO getUserById(Long id){
        return userMapper.toUserDTO(userRepository.findById(id).orElse(null));
    }
    public UserDTO getUserByEmail(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user == null || !user.getPassword().equals(password)){
            throw new IllegalArgumentException("Credential are incorrect");
        }else{
            return userMapper.toUserDTO(user);
        }
    }
    public UserDTO addUser(User user){
        return userMapper.toUserDTO(userRepository.save(user));
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
