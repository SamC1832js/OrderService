package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.DTO.UserDTO;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Service.impl.ShoppingCartServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.UserServiceImpl;
import com.example.rothurtech.orderservice.security.JWTGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;
    private final JWTGenerator jwtGenerator;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ShoppingCartServiceImpl shoppingCartServiceImpl, JWTGenerator jwtGenerator) {
        this.userServiceImpl = userServiceImpl;
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/validateToken")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token missing or invalid format");
        }
        String token = authHeader.substring(7);
        try {
            boolean isValid = jwtGenerator.validateToken(token);
            if (isValid) {
                return ResponseEntity.ok("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User regUser) {
        UserDTO user = userServiceImpl.addUser(regUser);
        shoppingCartServiceImpl.addShoppingCart(regUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        // Check if username and password match
        boolean authenticated = userServiceImpl.authenticateUser(user.getEmail(), user.getPassword());

        if (authenticated) {
            // Create Authentication object with user's details
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null, // no need to store password here
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // Grant role to user
            );

            // Set the Authentication object in the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            Map<String, String> response = new HashMap<>();
            response.put("Authorization", "Bearer " + token);
            response.put("message", "Login successful.");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid username or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @GetMapping()
    public ResponseEntity<UserDTO> getUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserDTO user = userServiceImpl.getUserByEmail(email, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserDTO user = userServiceImpl.getUserByEmail(email, password);
        userServiceImpl.deleteUser(user.getId());
        return new ResponseEntity<>("User has been deleted.", HttpStatus.NO_CONTENT);
    }
}
