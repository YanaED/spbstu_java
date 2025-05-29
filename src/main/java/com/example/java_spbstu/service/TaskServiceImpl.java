package com.example.java_spbstu.service;

import com.example.java_spbstu.repo.TaskRepository;
import com.example.java_spbstu.dto.TaskDto;
import com.example.java_spbstu.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(TaskDto dto) {
        Task task = new Task();
        task.setId(randomUUID());
        task.setUserId(dto.getUserId());
        task.setTitle(dto.getTitle());
        task.setCreatedAt(LocalDateTime.now());
        task.setTargetDate(dto.getTargetDate());
        task.setCompleted(false);
        task.setDeleted(false);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public List<Task> getPendingTasks(String userId) {
        return taskRepository.findPendingByUserId(userId);
    }

    @Override
    public void markTaskAsDeleted(UUID uuid) {
        taskRepository.markAsDeleted(uuid);
    }
}
