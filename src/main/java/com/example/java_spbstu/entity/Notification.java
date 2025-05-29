package com.example.java_spbstu.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Notification {
    private UUID id;
    private String message;
    private String userId;
    private boolean processed;
}
