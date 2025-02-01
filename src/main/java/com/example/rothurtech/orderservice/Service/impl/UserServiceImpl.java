package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.DTO.UserDTO;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Mapper.UserMapper;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import com.example.rothurtech.orderservice.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getUserById(Long id){
        return userMapper.toUserDTO(userRepository.findById(id).orElse(null));
    }

    public UserDTO getCurrentUser(HttpServletRequest request) {
        // Get user email from JWT token in SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user =  getUserByEmailAuth(email);
        return userMapper.toUserDTO(user);
    }

    public UserDTO getUserByEmail(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user == null || !user.getPassword().equals(password)){
            throw new IllegalArgumentException("Credential are incorrect");
        }else{
            return userMapper.toUserDTO(user);
        }
    }

    public boolean authenticateUser(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Compare the raw password with the stored encrypted password
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public User getUserByEmailAuth(String email){
        return userRepository.findByEmail(email);
    }


    public UserDTO addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserDTO(userRepository.save(user));
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
