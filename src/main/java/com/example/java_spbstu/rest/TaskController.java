package com.example.java_spbstu.rest;

import com.example.java_spbstu.dto.TaskDto;
import com.example.java_spbstu.entity.Task;
import com.example.java_spbstu.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks(@RequestParam("userId") String userId, @RequestParam(value = "pending", required = false) Boolean pending) {
        var result = pending == null || !pending ? taskService.getAllTasks(userId) : taskService.getPendingTasks(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDto dto) {
        return new ResponseEntity<>(taskService.createTask(dto), CREATED);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.markTaskAsDeleted(id);
        return ResponseEntity.noContent().build();
    }
}
