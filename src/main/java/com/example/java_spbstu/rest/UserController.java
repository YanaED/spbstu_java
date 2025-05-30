package com.example.java_spbstu.rest;

import com.example.java_spbstu.dto.UserDto;
import com.example.java_spbstu.entity.User;
import com.example.java_spbstu.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> register(@RequestBody @Valid UserDto dto) {
        return new ResponseEntity<>(userService.register(dto), HttpStatus.CREATED);
    }

    @GetMapping("/users/login")
    public ResponseEntity<User> login(@RequestParam("username") String username) {
        User user = userService.login(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
