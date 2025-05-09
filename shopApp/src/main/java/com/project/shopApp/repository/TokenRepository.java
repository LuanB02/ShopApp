package com.project.shopApp.repository;

import com.project.shopApp.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    Optional<Token> findByUserId(Long userId);
} 