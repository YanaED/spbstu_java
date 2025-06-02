package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("db")
@RequiredArgsConstructor
public class RealNotificationRepository implements NotificationRepository {

    private final NotificationJpaRepository jpaRepo;

    @Override
    @Transactional
    public List<Notification> findAll(String userId) {
        return jpaRepo.findByUserId(userId);
    }

    @Override
    @Transactional
    public void save(Notification notification) {
        jpaRepo.save(notification);
    }

    @Override
    @Transactional
    public List<Notification> findPending(String userId) {
        return jpaRepo.findByUserIdAndNotRead(userId);
    }
}
