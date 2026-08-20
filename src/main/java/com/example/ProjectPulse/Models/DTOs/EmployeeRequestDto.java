package com.example.ProjectPulse.Models.DTOs;

import com.example.ProjectPulse.Enums.EmployeeType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmployeeRequestDto(
        @NotBlank(message = "Employee name can not be blank")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Employee name must contain letters only")
        String employeeName,

        @NotBlank(message = "Employee email can not be blank")
        @Email(message = "Email format is invalid")
        String employeeEmail,

        @NotBlank(message = "Password can not be blank")
        String password,

        EmployeeType employeeType
) {}
