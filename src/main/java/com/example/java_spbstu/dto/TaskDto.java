package com.example.java_spbstu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotBlank
    private String title;
    @NotBlank
    private String userId;
    @NotNull
    private LocalDateTime targetDate;
}
