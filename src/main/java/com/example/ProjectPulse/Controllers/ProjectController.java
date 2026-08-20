package com.example.ProjectPulse.Controllers;

import com.example.ProjectPulse.Models.DTOs.ProjectRequestDto;
import com.example.ProjectPulse.Models.DTOs.ProjectResponseDto;
import com.example.ProjectPulse.Services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto){
            ProjectResponseDto projectResponseDto = projectService.createProject(projectRequestDto);
            return new ResponseEntity<>(projectResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable int projectId)  {
        ProjectResponseDto projectResponseDto = projectService.findProjectById(projectId);
        return new ResponseEntity<>(projectResponseDto,HttpStatus.OK);
    }

    @PutMapping("/{projectId}/employee/{employeeId}")
    public ResponseEntity<ProjectResponseDto> addEmployee(@PathVariable int projectId,@PathVariable int employeeId) {
        ProjectResponseDto projectResponseDto = projectService.addEmployee(projectId,employeeId);
        return new ResponseEntity<>(projectResponseDto,HttpStatus.OK);
    }
}
