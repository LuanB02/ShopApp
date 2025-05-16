package com.project.shopApp.service.impl;

import com.project.shopApp.common.exceptions.CheckEmptyCategoriesException;
import com.project.shopApp.common.exceptions.CheckEmptyCategoryException;
import com.project.shopApp.common.exceptions.CheckEmptyOrderException;
import com.project.shopApp.common.exceptions.CheckEmptyUserException;
import com.project.shopApp.dtos.OrderDto;
import com.project.shopApp.dtos.response.OrderResponseDto;
import com.project.shopApp.mapper.OrderMapping;
import com.project.shopApp.models.Order;
import com.project.shopApp.models.OrderStatus;
import com.project.shopApp.models.User;
import com.project.shopApp.repository.OrderRepository;
import com.project.shopApp.repository.UserRepository;
import com.project.shopApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponseDto createOrder(OrderDto orderDto) {
        Optional<User> user = userRepository.findById(orderDto.getUserId());
        if (user.isPresent()) {
            orderDto.setStatus(OrderStatus.PENDING);
            orderDto.setActive(true);
            Order order = orderRepository.save(OrderMapping.INSTANCE.toModel(orderDto));

            return OrderMapping.INSTANCE.toOrderDto(order);
        } else {
            throw new CheckEmptyUserException();
        }
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return OrderMapping.INSTANCE.toDto(order.get());
        } else {
            throw new CheckEmptyOrderException(id);
        }
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.getOrderById(id);
        if (order != null){
            order.setAddress(orderDto.getAddress());
            order.setEmail(orderDto.getEmail());
            order.setNote(orderDto.getNote());
            order.setFullName(orderDto.getFullName());
            order.setPaymentMethod(orderDto.getPaymentMethod());
            order.setPhoneNumber(orderDto.getPhoneNumber());
            order.setShippingMethod(orderDto.getShippingMethod());
            order.setTotalMoney(orderDto.getTotalMoney());

            return OrderMapping.INSTANCE.toDto(orderRepository.save(order));
        } else {
            throw new CheckEmptyOrderException(id);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
        } else {
            throw new CheckEmptyOrderException(id);
        }
    }

    @Override
    public List<OrderDto> getAllOrders(Long userId) {
        return List.of();
    }
}
