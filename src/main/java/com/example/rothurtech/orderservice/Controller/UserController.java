package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.DTO.UserDTO;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Service.impl.ShoppingCartServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ShoppingCartServiceImpl shoppingCartServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
    }

    @PostMapping()
    public ResponseEntity<String> registerUser(@RequestBody User regUser) {
        UserDTO user = userServiceImpl.addUser(regUser);
        shoppingCartServiceImpl.addShoppingCart(user.getId());
        return new ResponseEntity<>("Document user Id for later " + user.getId().toString(), HttpStatus.CREATED);
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
