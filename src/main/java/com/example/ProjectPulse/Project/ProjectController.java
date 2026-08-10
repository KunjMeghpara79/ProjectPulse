package com.example.ProjectPulse.Project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectDto projectDto){
        try {
            ProjectResponseDto project = projectService.createProject(projectDto);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable int projectId)  {
        ProjectResponseDto project = projectService.findProjectById(projectId);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @PutMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<ProjectResponseDto> addEmployee(@PathVariable int projectId,@PathVariable int employeeId) {
        ProjectResponseDto project = projectService.addEmployee(projectId,employeeId);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }
}
