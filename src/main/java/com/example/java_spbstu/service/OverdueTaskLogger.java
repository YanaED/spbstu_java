package com.example.java_spbstu.service;

import com.example.java_spbstu.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OverdueTaskLogger {

    @Async
    public void log(List<Task> overdueTasks) {
        if (!overdueTasks.isEmpty()) {
            log.info("Found {} overdue tasks: {}", overdueTasks.size(), overdueTasks.stream().map(task -> task.getId().toString()).collect(Collectors.joining(", ")));
        }
    }
}
