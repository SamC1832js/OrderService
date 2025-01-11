package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Repository.ShoppingCartRepository;
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

    @PutMapping("/{userId}/{productId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long userId, @PathVariable Long productId) {
        ShoppingCart cart = shoppingCartServiceImpl.addProductToShoppingCart(userId, productId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{userId}/{productId}/{quantity}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Integer quantity) {
        if(quantity == 0){
            shoppingCartServiceImpl.removeProductFromShoppingCart(userId, productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            ShoppingCart cart = shoppingCartServiceImpl.addProductToShoppingCart(userId, productId, quantity);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
    }



}
