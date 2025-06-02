package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
