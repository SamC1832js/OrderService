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
        return shoppingCartRepository.findByUserId(userId);
    }

    public ShoppingCart addShoppingCart(Long userId){
        ShoppingCart cart = new ShoppingCart();
        User user = userRepository.findById(userId).orElse(null);
        cart.setUser(user);
        shoppingCartRepository.save(cart);
        return cart;
    }
    public ShoppingCart addProductToShoppingCart(Long userId, Long productId){
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        Map<Product, Integer> products = cart.getProducts();
        Product product = productRepository.findById(productId).orElse(null);
        products.put(product, products.getOrDefault(product, 0) + 1);
        cart.setProducts(products);
        return shoppingCartRepository.save(cart);
    }


    public ShoppingCart addProductToShoppingCart(Long userId, String productName, int quantity){
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        Map<Product, Integer> products = cart.getProducts();
        Product product = productRepository.findByName(productName);
        products.put(product, products.getOrDefault(product, 0) + quantity);
        cart.setProducts(products);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart updateProductQuatityInShoppingCart(Long userId, String productName, int quantity){
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        Map<Product, Integer> products = cart.getProducts();
        Product product = productRepository.findByName(productName);
        products.put(product, quantity);
        cart.setProducts(products);
        return shoppingCartRepository.save(cart);
    }


    public void removeProductFromShoppingCart(Long userId, String productName){
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        Map<Product, Integer> products = cart.getProducts();
        Product product = productRepository.findByName(productName);
        products.remove(product);
        cart.setProducts(products);
        shoppingCartRepository.delete(cart);
        shoppingCartRepository.save(cart);
    }
    public void clearShoppingCart(Long userId){
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        Map<Product, Integer> cart = shoppingCart.getProducts();
        cart.clear();
        shoppingCart.setProducts(cart);
    }
}
