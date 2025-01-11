package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShoppingCartImpl {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    ShoppingCart getShoppingCart(User user){
        return shoppingCartRepository.findByUser(user);
    }

    ShoppingCart addProductToShoppingCart(ShoppingCart shoppingCart, Product product){
        Map<Product, Integer> cart = shoppingCart.getProducts();
        cart.put(product, 1);
        shoppingCart.setProducts(cart);
        return shoppingCartRepository.save(shoppingCart);
    }

    ShoppingCart addProductToShoppingCart(ShoppingCart shoppingCart, Product product, int quantity){
        Map<Product, Integer> cart = shoppingCart.getProducts();
        cart.put(product, quantity);
        shoppingCart.setProducts(cart);
        return shoppingCartRepository.save(shoppingCart);
    }

    ShoppingCart updateProductToShoppingCart(ShoppingCart shoppingCart, Product product, int quantity){
        return addProductToShoppingCart(shoppingCart, product, quantity);
    }
    void removeProductFromShoppingCart(ShoppingCart shoppingCart, Product product){
        Map<Product, Integer> cart = shoppingCart.getProducts();
        cart.remove(product);
        shoppingCart.setProducts(cart);
    }
    void clearShoppingCart(ShoppingCart shoppingCart){
        Map<Product, Integer> cart = shoppingCart.getProducts();
        cart.clear();
        shoppingCart.setProducts(cart);
    }
}
