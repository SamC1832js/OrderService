package com.example.rothurtech.orderservice.Service.impl;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Product product = productRepository.findByName(productName);
        if(product == null) {
            throw new IllegalArgumentException("Product does not exist: " + productName);
        }
        return product;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public Product updateProduct(String productName, Product product){
        Product existingProduct = getProduct(productName);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    public Product updateProduct(String productName, Map<String, Object> updates){
        Product existingProduct = getProduct(productName);
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> existingProduct.setName((String) value);
                case "description" -> existingProduct.setDescription((String) value);
                case "price" -> existingProduct.setPrice(Double.parseDouble(value.toString()));
            }});
        return productRepository.save(existingProduct);
    }
    public void deleteProduct(String productName){
        Product existingProduct = getProduct(productName);
        productRepository.delete(existingProduct);
    }
}
