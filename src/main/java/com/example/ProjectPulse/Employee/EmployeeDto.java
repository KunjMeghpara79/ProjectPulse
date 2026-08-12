package com.example.ProjectPulse.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeDto(
        @NotBlank(message = "Employee name can not be blank")
        String employeeName,

        @NotBlank(message = "Employee email can not be blank")
        @Email(message = "Email format is invalid")
        String employeeEmail
) {}
