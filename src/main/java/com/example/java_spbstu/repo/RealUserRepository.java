package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("db")
@RequiredArgsConstructor
public class RealUserRepository implements UserRepository {

    private final UserJpaRepository jpaRepo;

    @Override
    @Transactional
    public User save(User user) {
        return jpaRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return jpaRepo.findByUsername(username);
    }
}
