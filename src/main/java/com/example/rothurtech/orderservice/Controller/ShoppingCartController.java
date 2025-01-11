package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Service.impl.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartServiceImpl shoppingCartServiceImpl;

    @Autowired
    public ShoppingCartController(ShoppingCartServiceImpl shoppingCartServiceImpl) {
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long userId) {
        ShoppingCart cart = shoppingCartServiceImpl.getShoppingCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ShoppingCart> addProductToShoppingCart(@PathVariable Long userId, @RequestParam(value = "productname") String productName, @RequestParam(value = "quantity", required = false, defaultValue = "1")  Integer quantity) {
        ShoppingCart cart = shoppingCartServiceImpl.addProductToShoppingCart(userId, productName, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long userId, @RequestParam(value = "productname") String productName, @RequestParam(value = "quantity")  Integer quantity) {
        ShoppingCart cart = shoppingCartServiceImpl.updateProductQuatityInShoppingCart(userId, productName, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<ShoppingCart> removeProductFromShoppingCart(@PathVariable Long userId, @RequestParam(value = "productname") String productName) {
        shoppingCartServiceImpl.removeProductFromShoppingCart(userId, productName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<ShoppingCart> clearShoppingCart(@PathVariable Long userId) {
        shoppingCartServiceImpl.clearShoppingCart(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
