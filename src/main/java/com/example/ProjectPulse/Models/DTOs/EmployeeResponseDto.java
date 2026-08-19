package com.example.ProjectPulse.Models.DTOs;

import com.example.ProjectPulse.Enums.EmployeeType;

public record EmployeeResponseDto(int employeeId,
                                  String employeeName,
                                  String employeeEmail,
                                  EmployeeType employeeType) {
}
