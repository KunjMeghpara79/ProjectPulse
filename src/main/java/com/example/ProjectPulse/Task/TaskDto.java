package com.example.ProjectPulse.Task;

public record TaskDto(
        String taskDetails,
        int projectId,
        int employeeId
) {}
