package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Repository
@Profile("simple")
public class SimpleTaskRepository implements TaskRepository {

    private static final Map<UUID, Task> DATA = new HashMap<>();

    @Override
    public Task save(Task task) {
        return Optional.of(task).stream().peek(t -> DATA.put(t.getId(), t)).findFirst().get();
    }

    @Override
    public List<Task> findAllByUserId(String userId) {
        return DATA.values().stream().filter(t -> t.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<Task> findPendingByUserId(String userId) {
        return DATA.values().stream()
                   .filter(t -> t.getUserId().equals(userId) && !t.isCompleted() && !t.isDeleted())
                   .collect(Collectors.toList());
    }

    @Override
    public void markAsDeleted(UUID taskId) {
        Task task = DATA.get(taskId);
        if (task != null) {
            task.setDeleted(true);
        }
    }

    @Override
    public List<Task> findOverdueTasks() {
        return DATA.values().stream().filter(t -> now().isAfter(t.getTargetDate())).toList();
    }
}
