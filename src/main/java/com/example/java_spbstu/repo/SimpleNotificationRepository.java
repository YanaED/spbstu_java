package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("simple")
public class SimpleNotificationRepository implements NotificationRepository {

    private static final List<Notification> DATA = new ArrayList<>();

    @Override
    public List<Notification> findAll(String userId) {
        return DATA.stream().filter(n -> n.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<Notification> findPending(String userId) {
        return DATA.stream().filter(n -> n.getUserId().equals(userId) && !n.isProcessed()).collect(Collectors.toList());
    }

    @SuppressWarnings("unused")
    public void save(Notification notification) {
        DATA.add(notification);
    }
}
