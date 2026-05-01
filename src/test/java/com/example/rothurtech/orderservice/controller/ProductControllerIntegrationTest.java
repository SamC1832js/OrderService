package com.example.rothurtech.orderservice.controller;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        testProduct = new Product();
        testProduct.setName("Integration Test Product");
        testProduct.setDescription("Integration Test Description");
        testProduct.setPrice(99.99);
        testProduct = productRepository.save(testProduct);
    }

    @Test
    void getAllProducts_ShouldReturnProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Integration Test Product"))
                .andExpect(jsonPath("$[0].description").value("Integration Test Description"))
                .andExpect(jsonPath("$[0].price").value(99.99));
    }

    @Test
    void getProductById_WithValidId_ShouldReturnProduct() throws Exception {
        mockMvc.perform(get("/api/products/" + testProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Integration Test Product"));
    }

    @Test
    void addProduct_ShouldCreateNewProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("New Test Product");
        newProduct.setDescription("New Test Description");
        newProduct.setPrice(149.99);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Test Product"))
                .andExpect(jsonPath("$.description").value("New Test Description"))
                .andExpect(jsonPath("$.price").value(149.99));
    }

    @Test
    void updateProduct_WithValidName_ShouldUpdateProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(199.99);

        mockMvc.perform(put("/api/products")
                .param("productName", testProduct.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    @Test
    void deleteProduct_WithValidName_ShouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products")
                .param("productName", testProduct.getName()))
                .andExpect(status().isNoContent());

        // Verify product is deleted
        mockMvc.perform(get("/api/products/" + testProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
} 