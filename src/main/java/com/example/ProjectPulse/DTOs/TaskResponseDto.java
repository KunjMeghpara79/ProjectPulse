package com.example.ProjectPulse.DTOs;

import com.example.ProjectPulse.Enums.TaskStatus;

public record TaskResponseDto(int taskId,
                              String taskDetails,
                              TaskStatus taskStatus,
                              int projectId,
                              int employeeId) {
}
