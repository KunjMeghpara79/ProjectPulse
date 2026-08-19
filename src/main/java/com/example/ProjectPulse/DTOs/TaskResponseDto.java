package com.example.ProjectPulse.DTOs;

import com.example.ProjectPulse.Entities.Employee;
import com.example.ProjectPulse.Enums.TaskStatus;

public record TaskResponseDto(int taskId,
                              String taskDetails,
                              TaskStatus taskStatus,
                              int employeeId) {
}
