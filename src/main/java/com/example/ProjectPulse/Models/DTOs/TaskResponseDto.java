package com.example.ProjectPulse.Models.DTOs;

import com.example.ProjectPulse.Enums.TaskStatus;

public record TaskResponseDto(int taskId,
                              String taskDetails,
                              TaskStatus taskStatus,
                              int employeeId) {
}
