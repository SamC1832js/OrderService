package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.Entity.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(int id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(String name, Product product);
    void deleteProduct(int id);
}
