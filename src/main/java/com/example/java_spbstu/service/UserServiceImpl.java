package com.example.java_spbstu.service;

import com.example.java_spbstu.repo.UserRepository;
import com.example.java_spbstu.dto.UserDto;
import com.example.java_spbstu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User login(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(UserDto dto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(dto.getUsername());
        return userRepository.save(user);
    }
}
