package com.example.ProjectPulse.Models.DTOs;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDto(
        @NotBlank(message = "Task detail can not be blank.")
        String taskDetails,
        int projectId,
        int employeeId
) {}
