package com.example.java_spbstu.repo;

import com.example.java_spbstu.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("db")
@RequiredArgsConstructor
public class RealTaskRepository implements TaskRepository {

    private final TaskJpaRepository jpaRepository;

    @Override
    @Transactional
    public Task save(Task task) {
        return jpaRepository.save(task);
    }

    @Override
    @Transactional
    public List<Task> findAllByUserId(String userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public List<Task> findPendingByUserId(String userId) {
        return jpaRepository.findByUserIdAndNotCompletedAndNotDeleted(userId);
    }

    @Override
    @Transactional
    public void markAsDeleted(UUID taskId) {
        jpaRepository.findById(taskId).ifPresent(task -> {
            task.setDeleted(true);
            jpaRepository.save(task);
        });
    }
}
