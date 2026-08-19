package com.example.ProjectPulse.Models.DTOs;

import java.util.List;

public record ProjectResponseDto(int projectId,
                                 String projectName,
                                 List<EmployeeResponseDto> employees,
                                 List<TaskResponseDto> tasks) {
}
