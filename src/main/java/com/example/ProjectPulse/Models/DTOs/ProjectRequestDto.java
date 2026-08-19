package com.example.ProjectPulse.Models.DTOs;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequestDto(
        @NotBlank(message = "Project name can not be blank")
        String projectName
) {}
