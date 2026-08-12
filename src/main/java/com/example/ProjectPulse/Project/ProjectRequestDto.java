package com.example.ProjectPulse.Project;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequestDto(
        @NotBlank(message = "Project name can not be blank")
        String projectName
) {}
