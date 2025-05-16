package com.project.shopApp.controller;

import com.project.shopApp.dtos.OrderDto;
import com.project.shopApp.dtos.response.OrderResponseDto;
import com.project.shopApp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto){
        try {
            OrderResponseDto orderResponseDto = orderService.createOrder(orderDto);
            return ResponseEntity.ok(orderResponseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersById(@Valid @PathVariable Long id){
        try {
            OrderDto orderDto = orderService.getOrderById(id);

            return ResponseEntity.ok(orderDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersFromUser(@Valid @PathVariable Long userId){
        try {
            return ResponseEntity.ok("Get successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrdersFromUser(@Valid @PathVariable Long id,
                                               @Valid @RequestBody OrderDto orderDto){
        try {
            OrderDto orderDto1 = orderService.updateOrder(id, orderDto);
            return ResponseEntity.ok(orderDto1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrdersFromUser(@Valid @PathVariable Long id){
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
