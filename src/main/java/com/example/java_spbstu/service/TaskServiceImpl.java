package com.example.java_spbstu.service;

import com.example.java_spbstu.amqp.TaskCreatedEvent;
import com.example.java_spbstu.amqp.TaskEventPublisher;
import com.example.java_spbstu.dto.TaskDto;
import com.example.java_spbstu.entity.Task;
import com.example.java_spbstu.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskEventPublisher taskEventPublisher;
    private final OverdueTaskLogger logger = new OverdueTaskLogger();

    @Override
    @CacheEvict(value = {"task", "task.pending"}, allEntries = true)
    public Task createTask(TaskDto dto) {
        Task task = new Task();
        task.setId(randomUUID());
        task.setUserId(dto.getUserId());
        task.setTitle(dto.getTitle());
        task.setCreatedAt(LocalDateTime.now());
        task.setTargetDate(dto.getTargetDate());
        task.setCompleted(false);
        task.setDeleted(false);
        task = taskRepository.save(task);
        taskEventPublisher.publishTaskCreated(new TaskCreatedEvent(
                task.getId(),
                task.getTitle(),
                task.getUserId(),
                task.getTargetDate(),
                task.getCreatedAt()
        ));
        return task;
    }

    @Override
    @Cacheable(value = "task", key = "#userId")
    public List<Task> getAllTasks(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Cacheable(value = "task.pending", key = "#userId")
    public List<Task> getPendingTasks(String userId) {
        return taskRepository.findPendingByUserId(userId);
    }

    @Override
    @CacheEvict(value = {"task", "task.pending"}, allEntries = true)
    public void markTaskAsDeleted(UUID uuid) {
        taskRepository.markAsDeleted(uuid);
    }

    @Scheduled(fixedRate = 3600000)
    public void checkOverdueTasks() {
        log.info("Checking for overdue tasks");
        logger.log(taskRepository.findOverdueTasks());
    }
}
