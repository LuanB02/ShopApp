package com.project.shopApp.mapper;

import com.project.shopApp.dtos.OrderDto;
import com.project.shopApp.dtos.response.OrderResponseDto;
import com.project.shopApp.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapping {
    OrderMapping INSTANCE = Mappers.getMapper(OrderMapping.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "active", ignore = true)
    OrderDto toDto(Order order);

    List<OrderDto> toListDto(List<Order> orders);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "active", target = "active")
    Order toModel(OrderDto orderDto);

    List<Order> toListModel(List<OrderDto> orderDtos);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "active", target = "active")
    OrderResponseDto toOrderDto(Order order);
}
