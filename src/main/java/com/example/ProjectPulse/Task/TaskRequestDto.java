package com.example.ProjectPulse.Task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TaskRequestDto(
        @NotBlank(message = "Task detail can not be blank.")
        String taskDetails,

        @Min(value = 1,message = "Project id should be greater than zero")
        int projectId,

        @Min(value = 1,message = "Employee id should be greater than zero")
        int employeeId
) {}
