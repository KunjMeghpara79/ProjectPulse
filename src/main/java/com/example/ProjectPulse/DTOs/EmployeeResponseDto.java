package com.example.ProjectPulse.DTOs;

import com.example.ProjectPulse.Enums.EmployeeType;

public record EmployeeResponseDto(int employeeId, String employeeName, String employeeEmail, EmployeeType employeeType) {
}
