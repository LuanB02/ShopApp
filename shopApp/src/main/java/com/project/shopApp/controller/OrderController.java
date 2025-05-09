package com.project.shopApp.controller;

import com.project.shopApp.dtos.OrderDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @PostMapping()
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto){
        try {
            return ResponseEntity.ok("Create successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersFromUser(@Valid @PathVariable Long id){
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
            return ResponseEntity.ok("Update successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrdersFromUser(@Valid @PathVariable Long id){
        try {
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
