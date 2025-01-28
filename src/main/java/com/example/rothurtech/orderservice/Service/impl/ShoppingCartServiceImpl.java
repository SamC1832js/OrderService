package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.DTO.ShoppingCartDTO;
import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Entity.ShoppingCart;
import com.example.rothurtech.orderservice.Entity.User;
import com.example.rothurtech.orderservice.Mapper.ShoppingCartMapper;
import com.example.rothurtech.orderservice.Repository.ProductRepository;
import com.example.rothurtech.orderservice.Repository.ShoppingCartRepository;
import com.example.rothurtech.orderservice.Repository.UserRepository;
import com.example.rothurtech.orderservice.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShoppingCartServiceImpl {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.shoppingCartMapper = shoppingCartMapper;
    }
    public ShoppingCart getShoppingCart(long userId){
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Shopping cart not found for user ID: " + userId);
        }
        return cart;
    }

    public ShoppingCartDTO getShoppingCartDTO(long userId){
        ShoppingCart cart = getShoppingCart(userId);
        return shoppingCartMapper.toShoppingCartDTO(cart);
    }

    public Product getProduct(String productName){
        Product product = productRepository.findByName(productName);
        if (product == null) {
            throw new IllegalArgumentException("Product not found for product name: " + productName);
        }
        return product;
    }

    public void addShoppingCart(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        ShoppingCart cart = new ShoppingCart();
        cart.setTotalPrice(0.0);
        cart.setUser(user);
        shoppingCartRepository.save(cart);
    }
    public ShoppingCartDTO addProductToShoppingCart(long userId, long productId){
        ShoppingCart cart = getShoppingCart(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Map<Product, Integer> products = cart.getProducts();
        products.put(product, products.getOrDefault(product, 0) + 1);
        return shoppingCartMapper.toShoppingCartDTO(shoppingCartRepository.save(cart));
    }

    public ShoppingCartDTO addProductToShoppingCart(long userId, String productName, int quantity){
        ShoppingCart cart = getShoppingCart(userId);
        Product product = getProduct(productName);
        Map<Product, Integer> products = cart.getProducts();
        cart.setTotalPrice(calculateTotal(product, quantity + products.getOrDefault(product, 0), products.getOrDefault(product, 0), cart.getTotalPrice()));
        products.put(product, products.getOrDefault(product, 0) + quantity);
        return shoppingCartMapper.toShoppingCartDTO(shoppingCartRepository.save(cart));
    }

    public ShoppingCartDTO updateProductQuatityInShoppingCart(long userId, String productName, int quantity){
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        ShoppingCart cart = getShoppingCart(userId);
        Product product = getProduct(productName);
        Map<Product, Integer> products = cart.getProducts();
        if (quantity == 0) {
            cart.setTotalPrice(calculateTotal(product, 0, products.get(product), cart.getTotalPrice()));
            products.remove(product);
        } else {
            cart.setTotalPrice(calculateTotal(product, quantity, products.getOrDefault(product, 0), cart.getTotalPrice()));
            products.put(product, quantity);
        }
        return shoppingCartMapper.toShoppingCartDTO(shoppingCartRepository.save(cart));
    }


    public void removeProductFromShoppingCart(long userId, String productName){
        ShoppingCart cart = getShoppingCart(userId);
        Product product = getProduct(productName);
        Map<Product, Integer> products = cart.getProducts();
        Integer productQuantity = products.get(product);

        if (productQuantity == null) {
            throw new IllegalArgumentException("Product not found in shopping cart.");
        }
        cart.setTotalPrice(calculateTotal(product, 0, products.get(product), cart.getTotalPrice()));
        products.remove(product);
        shoppingCartRepository.save(cart);
    }
    public void clearShoppingCart(long userId){
        ShoppingCart cart = getShoppingCart(userId);
        cart.getProducts().clear();
        cart.setTotalPrice(0.0);
        shoppingCartRepository.save(cart);
    }

    public Double calculateTotal(Product product, int quantity, int quantityBefore, Double priceBefore){
        if(quantity == 0){
            return priceBefore - (product.getPrice() * quantityBefore);
        }
        return priceBefore + (product.getPrice() * (quantity - quantityBefore));
    }
}
