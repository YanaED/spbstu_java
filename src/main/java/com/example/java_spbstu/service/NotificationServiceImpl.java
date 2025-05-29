package com.example.java_spbstu.service;

import com.example.java_spbstu.repo.NotificationRepository;
import com.example.java_spbstu.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAll(String userId) {
        return notificationRepository.findAll(userId);
    }

    @Override
    public List<Notification> getPending(String userId) {
        return notificationRepository.findPending(userId);
    }
}
