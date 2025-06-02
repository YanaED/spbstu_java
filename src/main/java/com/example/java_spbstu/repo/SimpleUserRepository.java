package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("simple")
public class SimpleUserRepository implements UserRepository {

    private static final Map<String, User> DATA = new HashMap<>();

    @Override
    public User save(User user) {
        return Optional.of(user).stream().peek(u -> DATA.put(u.getId(), u)).findFirst().get();
    }

    @Override
    public User findByUsername(String username) {
        return DATA.values().stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
    }
}
