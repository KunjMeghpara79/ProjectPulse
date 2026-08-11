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
            ProjectResponseDto projectResponseDto = projectService.createProject(projectDto);
            return new ResponseEntity<>(projectResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
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

    @PutMapping("/{projectId}/task/{taskId}")
    public ResponseEntity<ProjectResponseDto> addTask(@PathVariable int projectId,@PathVariable int taskId){
        ProjectResponseDto projectResponseDto = projectService.addTask(projectId,taskId);
        return new ResponseEntity<>(projectResponseDto,HttpStatus.OK);
    }
}
