package com.example.java_spbstu.amqp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreatedEvent {
    private UUID taskId;
    private String title;
    private String userId;
    private LocalDateTime targetDate;
    private LocalDateTime createdAt;
}