package com.project.shopApp.repository;

import com.project.shopApp.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    Page<Product> findAll(Pageable pageable);
}
