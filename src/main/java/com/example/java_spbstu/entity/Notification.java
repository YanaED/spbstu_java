package com.example.java_spbstu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    private UUID id;
    private String message;
    private String userId;
    private boolean processed;
}
