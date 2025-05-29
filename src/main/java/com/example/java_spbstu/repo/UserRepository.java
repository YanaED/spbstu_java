package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.User;

public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
