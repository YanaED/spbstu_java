package com.example.java_spbstu.service;

import com.example.java_spbstu.dto.TaskDto;
import com.example.java_spbstu.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getAllTasks(String userId);
    List<Task> getPendingTasks(String userId);
    Task createTask(TaskDto task);
    void markTaskAsDeleted(UUID taskId);
}
