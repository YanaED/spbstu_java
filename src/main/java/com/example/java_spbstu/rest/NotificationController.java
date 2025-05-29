package com.example.java_spbstu.rest;

import com.example.java_spbstu.entity.Notification;
import com.example.java_spbstu.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notifications/pending")
    public ResponseEntity<List<Notification>> findByUserPending(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(notificationService.getPending(userId));
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> findByUser(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(notificationService.getAll(userId));
    }
}
