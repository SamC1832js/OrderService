package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productServiceImpl.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productServiceImpl.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestParam(value = "productname") String productName, @RequestBody Product product) {
        Product updatedProduct = productServiceImpl.updateProduct(productName, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@RequestParam(value = "productname") String productName) {
        productServiceImpl.deleteProduct(productName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestParam(value = "productname") String productName, @RequestBody Map<String, Object> updates ) {
        Product updatedProduct = productServiceImpl.getProduct(productName);
        if(updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> updatedProduct.setName((String) value);
                case "description" -> updatedProduct.setDescription((String) value);
                case "price" -> updatedProduct.setPrice(Double.parseDouble(value.toString()));
        }});
        return new ResponseEntity<>(productServiceImpl.updateProduct(productName, updatedProduct), HttpStatus.OK);
    }

}
