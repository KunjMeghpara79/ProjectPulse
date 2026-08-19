package com.example.ProjectPulse.DTOs;

import com.example.ProjectPulse.Entities.Employee;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TaskRequestDto(
        @NotBlank(message = "Task detail can not be blank.")
        String taskDetails,
        int projectId,
        int employeeId
) {}
