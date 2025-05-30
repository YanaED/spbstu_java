package com.example.java_spbstu.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${spring.rabbitmq.routingkey.task-created}")
    private String taskCreatedRoutingKey;
    
    @Autowired
    public TaskEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void publishTaskCreated(TaskCreatedEvent event) {
        try {
            log.info("Publishing task created event for task ID: {}", event.getTaskId());
            rabbitTemplate.convertAndSend(exchange, taskCreatedRoutingKey, event);
            log.info("Task created event published successfully");
        } catch (Exception e) {
            log.error("Error publishing task created event: {}", e.getMessage(), e);
        }
    }
}