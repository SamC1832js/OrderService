package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Service.UserService;
import com.example.rothurtech.orderservice.Service.impl.OrderServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.ShoppingCartServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userServiceImpl.addUser(user);
        shoppingCartServiceImpl.addShoppingCart(user.getId());
        return new ResponseEntity<>("Document user Id for later user" + user.getId().toString(), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<User> getUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        User user = userServiceImpl.getUserByEmail(email, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        User user = userServiceImpl.getUserByEmail(email, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
