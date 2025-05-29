package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Notification;

import java.util.List;

public interface NotificationRepository {
    List<Notification> findPending(String userId);
    List<Notification> findAll(String userId);
    void save(Notification notification);
}
