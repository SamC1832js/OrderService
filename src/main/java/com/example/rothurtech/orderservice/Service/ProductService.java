package com.example.rothurtech.orderservice.Service;

import com.example.rothurtech.orderservice.Entity.Product;

import java.util.List;

public interface ProductService {
    public Product getProduct(int id);
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(String name, Product product);
    public void deleteProduct(int id);
}
