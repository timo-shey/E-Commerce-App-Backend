package com.example.ecommerce.repository;

import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
