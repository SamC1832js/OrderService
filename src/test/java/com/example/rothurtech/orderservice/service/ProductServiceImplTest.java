package com.example.rothurtech.orderservice.service;

import com.example.rothurtech.orderservice.Entity.Product;
import com.example.rothurtech.orderservice.Repository.ProductRepository;
import com.example.rothurtech.orderservice.Service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(99.99);
    }

    @Test
    void getProduct_WithValidId_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        Product result = productService.getProduct(1L);

        assertNotNull(result);
        assertEquals(testProduct.getName(), result.getName());
        assertEquals(testProduct.getPrice(), result.getPrice());
        verify(productRepository).findById(1L);
    }

    @Test
    void getProduct_WithValidName_ShouldReturnProduct() {
        when(productRepository.findByName("Test Product")).thenReturn(testProduct);

        Product result = productService.getProduct("Test Product");

        assertNotNull(result);
        assertEquals(testProduct.getName(), result.getName());
        assertEquals(testProduct.getPrice(), result.getPrice());
        verify(productRepository).findByName("Test Product");
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        List<Product> products = Arrays.asList(testProduct);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getName(), result.get(0).getName());
        verify(productRepository).findAll();
    }

    @Test
    void addProduct_ShouldSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        productService.addProduct(testProduct);

        verify(productRepository).save(testProduct);
    }

    @Test
    void updateProduct_WithValidName_ShouldUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Name");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(199.99);

        when(productRepository.findByName("Test Product")).thenReturn(testProduct);
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct("Test Product", updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        verify(productRepository).findByName("Test Product");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void deleteProduct_WithValidName_ShouldDeleteProduct() {
        when(productRepository.findByName("Test Product")).thenReturn(testProduct);

        productService.deleteProduct("Test Product");

        verify(productRepository).findByName("Test Product");
        verify(productRepository).delete(testProduct);
    }

    @Test
    void getProduct_WithInvalidName_ShouldThrowException() {
        when(productRepository.findByName("Invalid Product")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> 
            productService.getProduct("Invalid Product")
        );
    }
} 