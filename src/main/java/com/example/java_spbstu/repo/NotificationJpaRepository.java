package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NotificationJpaRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserId(String userId);

    @Query("select n from Notification n where n.userId = ?1 and n.processed = false")
    List<Notification> findByUserIdAndNotRead(String userId);
}
