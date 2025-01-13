package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Repository.ProductRepository;
import com.example.rothurtech.orderservice.Repository.ShoppingCartRepository;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShoppingCartServiceImpl {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    public ShoppingCart getShoppingCart(Long userId){
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Shopping cart not found for user ID: " + userId);
        }
        return cart;
    }

    public Product getProduct(String productName){
        Product product = productRepository.findByName(productName);
        if (product == null) {
            throw new IllegalArgumentException("Product not found for product name: " + productName);
        }
        return product;
    }

    public void addShoppingCart(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        shoppingCartRepository.save(cart);
    }
    public ShoppingCart addProductToShoppingCart(Long userId, Long productId){
        ShoppingCart cart = getShoppingCart(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Map<Product, Integer> products = cart.getProducts();
        products.put(product, products.getOrDefault(product, 0) + 1);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart addProductToShoppingCart(Long userId, String productName, int quantity){
        ShoppingCart cart = getShoppingCart(userId);
        Product product = getProduct(productName);
        Map<Product, Integer> products = cart.getProducts();
        products.put(product, products.getOrDefault(product, 0) + quantity);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart updateProductQuatityInShoppingCart(Long userId, String productName, int quantity){
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        ShoppingCart cart = getShoppingCart(userId);
        Product product = getProduct(productName);
        Map<Product, Integer> products = cart.getProducts();
        if (quantity == 0) {
            products.remove(product);
        } else {
            products.put(product, quantity);
        }
        return shoppingCartRepository.save(cart);
    }


    public void removeProductFromShoppingCart(Long userId, String productName){
        ShoppingCart cart = getShoppingCart(userId);
        Product product = getProduct(productName);
        Map<Product, Integer> products = cart.getProducts();
        if (products.remove(product) != null) {
            shoppingCartRepository.save(cart);
        }else{
            throw new IllegalArgumentException("Product not found in shopping cart.");
        }
    }
    public void clearShoppingCart(Long userId){
        ShoppingCart cart = getShoppingCart(userId);
        cart.getProducts().clear();
        shoppingCartRepository.save(cart);
    }

}
