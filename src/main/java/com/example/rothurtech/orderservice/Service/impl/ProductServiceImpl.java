package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getProduct(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public Product getProduct(String productName){
        return productRepository.findByName(productName);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(String productName, Product product){
        productRepository.deleteByName(productName);
        return productRepository.save(product);
    }
    public void deleteProduct(String productName){
        productRepository.deleteByName(productName);
    }
}
