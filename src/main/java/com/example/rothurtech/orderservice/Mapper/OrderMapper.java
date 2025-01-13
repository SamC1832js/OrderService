package com.example.rothurtech.orderservice.Mapper;

import com.example.rothurtech.orderservice.DTO.OrderDTO;
import com.example.rothurtech.orderservice.Entity.Order;
import org.mapstruct.Mapper;

//The componentModel = "spring" tells MapStruct to generate a Spring Bean for the mapper, making it available for dependency injection.
@Mapper(componentModel = "spring")
public interface OrderMapper {
    public OrderDTO toOrderDTO(Order order);
}
