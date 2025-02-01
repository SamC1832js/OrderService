package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.DTO.CartItemRequestDTO;
import com.example.rothurtech.orderservice.DTO.ShoppingCartDTO;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Service.impl.ShoppingCartServiceImpl;
import com.example.rothurtech.orderservice.Service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartServiceImpl shoppingCartServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public ShoppingCartController(ShoppingCartServiceImpl shoppingCartServiceImpl, UserServiceImpl userServiceImpl) {
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    private User getCurrentUser(HttpServletRequest request) {
        // Get user email from JWT token in SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user =  userServiceImpl.getUserByEmailAuth(email);
        System.out.println(email);
        return user;
    }

    @GetMapping()
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        ShoppingCartDTO cart = shoppingCartServiceImpl.getShoppingCartDTO(currentUser.getId());
        System.out.println(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ShoppingCartDTO> addProductToShoppingCart(HttpServletRequest request, @RequestBody CartItemRequestDTO cartItem) {
        User currentUser = getCurrentUser(request);
        ShoppingCartDTO cart = shoppingCartServiceImpl.addProductToShoppingCart(currentUser.getId(), cartItem.getProductName(), cartItem.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(HttpServletRequest request, @RequestBody CartItemRequestDTO cartItem) {
        User currentUser = getCurrentUser(request);
        ShoppingCartDTO cart = shoppingCartServiceImpl.updateProductQuatityInShoppingCart(currentUser.getId(), cartItem.getProductName(), cartItem.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ShoppingCartDTO> removeProductFromShoppingCart(HttpServletRequest request, @RequestParam String productName) {
        User currentUser = getCurrentUser(request);
        ShoppingCartDTO cart = shoppingCartServiceImpl.removeProductFromShoppingCart(currentUser.getId(), productName);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ShoppingCartDTO> clearShoppingCart(HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        ShoppingCartDTO cart = shoppingCartServiceImpl.clearShoppingCart(currentUser.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
