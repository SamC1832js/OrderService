package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.DTO.ShoppingCartDTO;
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
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable Long userId) {
        ShoppingCartDTO cart = shoppingCartServiceImpl.getShoppingCartDTO(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ShoppingCartDTO> addProductToShoppingCart(@PathVariable Long userId, @RequestParam(value = "productname") String productName, @RequestParam(value = "quantity", required = false, defaultValue = "1")  Integer quantity) {
        ShoppingCartDTO cart = shoppingCartServiceImpl.addProductToShoppingCart(userId, productName, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable Long userId, @RequestParam(value = "productname") String productName, @RequestParam(value = "quantity")  Integer quantity) {
        ShoppingCartDTO cart = shoppingCartServiceImpl.updateProductQuatityInShoppingCart(userId, productName, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeProductFromShoppingCart(@PathVariable Long userId, @RequestParam(value = "productname") String productName) {
        shoppingCartServiceImpl.removeProductFromShoppingCart(userId, productName);
        return new ResponseEntity<>("Product removed from your shopping cart.", HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<String> clearShoppingCart(@PathVariable Long userId) {
        shoppingCartServiceImpl.clearShoppingCart(userId);
        return new ResponseEntity<>("All products removed from your shopping cart.", HttpStatus.OK);
    }
}
