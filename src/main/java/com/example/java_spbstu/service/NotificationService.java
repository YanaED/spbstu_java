package com.example.java_spbstu.service;

import com.example.java_spbstu.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll(String userId);
    List<Notification> getPending(String userId);
}
