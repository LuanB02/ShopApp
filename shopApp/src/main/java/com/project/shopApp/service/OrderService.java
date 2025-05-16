package com.project.shopApp.service;

import com.project.shopApp.dtos.OrderDto;
import com.project.shopApp.dtos.response.OrderResponseDto;
import com.project.shopApp.models.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);

    List<OrderDto> getAllOrders(Long userId);
}
