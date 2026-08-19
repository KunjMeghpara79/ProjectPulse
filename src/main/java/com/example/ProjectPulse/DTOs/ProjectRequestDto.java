package com.example.ProjectPulse.DTOs;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequestDto(
        @NotBlank(message = "Project name can not be blank")
        String projectName
) {}
