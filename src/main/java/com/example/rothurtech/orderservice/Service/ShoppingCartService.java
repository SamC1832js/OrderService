package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(User user);
    ShoppingCart addProductToShoppingCart(ShoppingCart shoppingCart, Product product, int quantity);
    ShoppingCart updateProductToShoppingCart(ShoppingCart shoppingCart, Product product, int quantity);
    void removeProductFromShoppingCart(ShoppingCart shoppingCart, Product product);
    void clearShoppingCart(ShoppingCart shoppingCart);
    ShoppingCart purchase(ShoppingCart shoppingCart);
}
