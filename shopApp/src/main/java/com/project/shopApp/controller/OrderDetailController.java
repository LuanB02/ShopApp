package com.project.shopApp.controller;

import com.project.shopApp.dtos.OrderDetailDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orderDetail")
public class OrderDetailController {

    @PostMapping()
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDto orderDetailDto) {
        return ResponseEntity.ok("Create orderDetail");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable Long id){
        return ResponseEntity.ok("Create orderDetail");
    }
}
