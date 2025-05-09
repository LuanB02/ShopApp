package com.project.shopApp.repository;

import com.project.shopApp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
}
