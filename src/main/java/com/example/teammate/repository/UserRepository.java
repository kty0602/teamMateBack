package com.example.teammate.repository;

import com.example.teammate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(String id);

    User findByUsername(String username);
}