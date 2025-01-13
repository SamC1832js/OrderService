package com.example.rothurtech.orderservice.Mapper;

import com.example.rothurtech.orderservice.DTO.ProductDTO;
import com.example.rothurtech.orderservice.Entity.Product;
import org.mapstruct.Mapper;

//No need, product can be public
@Mapper(componentModel = "spring")
public interface ProductMapper {
    public ProductDTO toProductDTO(Product product);
}
