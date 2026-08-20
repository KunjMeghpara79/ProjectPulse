package com.example.ProjectPulse.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProjectRequestDto(
        @NotBlank(message = "Project name can not be blank")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Project name must contain letters only")
        String projectName
) {}
