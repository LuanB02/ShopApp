package com.project.shopApp.repository;

import com.project.shopApp.models.Order;
import com.project.shopApp.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
}
