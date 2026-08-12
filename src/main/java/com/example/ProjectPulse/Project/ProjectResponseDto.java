package com.example.ProjectPulse.Project;

import com.example.ProjectPulse.Employee.EmployeeResponseDto;
import com.example.ProjectPulse.Task.TaskResponseDto;

import java.util.List;

public record ProjectResponseDto(int projectId, String projectName, List<EmployeeResponseDto> employees, List<TaskResponseDto> tasks) {
}
