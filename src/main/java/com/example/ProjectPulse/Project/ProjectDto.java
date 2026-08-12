package com.example.ProjectPulse.Project;

import jakarta.validation.constraints.NotBlank;

public record ProjectDto(
        @NotBlank(message = "Project name can not be blank")
        String projectName
) {}
