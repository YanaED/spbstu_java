package com.example.java_spbstu.service;

import com.example.java_spbstu.dto.UserDto;
import com.example.java_spbstu.entity.User;

public interface UserService {
    User register(UserDto user);
    User login(String username);
}
