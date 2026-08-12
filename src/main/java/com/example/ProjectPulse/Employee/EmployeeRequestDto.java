package com.example.ProjectPulse.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRequestDto(
        @NotBlank(message = "Employee name can not be blank")
        String employeeName,

        @NotBlank(message = "Employee email can not be blank")
        @Email(message = "Email format is invalid")
        String employeeEmail
) {}
