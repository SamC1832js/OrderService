package com.example.rothurtech.orderservice.Controller;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Product> addProduct(@Validated @RequestBody Product product) {
        productServiceImpl.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestParam(value = "productName") String productName, @RequestBody Product product) {
        Product updatedProduct = productServiceImpl.updateProduct(productName, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestParam(value = "productName") String productName, @RequestBody Map<String, Object> updates ) {
        Product updatedProduct = productServiceImpl.updateProduct(productName, updates);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "productName") String productName) {
        productServiceImpl.deleteProduct(productName);
        return new ResponseEntity<>("Product deleted successfully.",HttpStatus.NO_CONTENT);
    }


}
