package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAllByUserId(String userId);
    List<Task> findPendingByUserId(String userId);
    void markAsDeleted(UUID taskId);
}
