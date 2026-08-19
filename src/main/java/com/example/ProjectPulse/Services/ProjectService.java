package com.example.ProjectPulse.Services;

import com.example.ProjectPulse.DTOs.ProjectRequestDto;
import com.example.ProjectPulse.DTOs.ProjectResponseDto;
import com.example.ProjectPulse.Entities.Employee;
import com.example.ProjectPulse.Mappers.ProjectMapper;
import com.example.ProjectPulse.Repositories.EmployeeRepo;
import com.example.ProjectPulse.Entities.Project;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import com.example.ProjectPulse.Exceptions.EmployeeNotInProjectException;
import com.example.ProjectPulse.Exceptions.ProjectAlreadyExistsException;
import com.example.ProjectPulse.Exceptions.ProjectNotFoundException;
import com.example.ProjectPulse.Entities.Task;
import com.example.ProjectPulse.Repositories.ProjectRepo;
import com.example.ProjectPulse.Repositories.TaskRepo;
import com.example.ProjectPulse.Enums.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
@Component : A class annotated by this annotation will be automatically registered as spring managed bean

@Service : This is a specialization of @Component, a class annotated with this annotation indicates that the class is having business logic

@Repository : This is also another specialization of @Component
which is having the feature of EXCEPTION TRANSLATION(an ability to convert the database
exception into spring's DataAccessException)

It tells the Spring container that the annotated class is responsible for the persistence layer, which handles database operations
*/
@Service
@Slf4j
public class ProjectService {
    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;
    private final ProjectMapper projectMapper;
    private final EmployeeRepo employeeRepo;
   //private final Logger log = LoggerFactory.getLogger(TaskService.class);
    public ProjectService(TaskService taskService, EmployeeService employeeService, ProjectRepo projectRepo, TaskRepo taskRepo, ProjectMapper projectMapper, EmployeeRepo employeeRepo) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
        this.projectMapper = projectMapper;
        this.employeeRepo = employeeRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto) {
        if (projectRepo.existsByProjectName(projectRequestDto.projectName())){
            log.error("Project already exists!");
            throw new ProjectAlreadyExistsException("Project already exists!");
        }
        Project project = projectMapper.projectRequestDtoToProject(projectRequestDto);
        projectRepo.save(project);
        return projectMapper.projectToProjectResponseDto(project);
    }

    public ProjectResponseDto findProjectById(int id) {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        Project project =  projectRepo.findById(id).orElseThrow(() -> {
            log.error("Project not found!");
            return new ProjectNotFoundException("Project not found!");
        });
        if(isAdmin){
            return projectMapper.projectToProjectResponseDto(project);
        }else {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Employee employee = employeeRepo.findByEmployeeEmail(email).orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"));
            if(!project.getEmployees().contains(employee)){
                throw new EmployeeNotInProjectException("You can not access the project information as you are not part of this project");
            }
            return projectMapper.projectToProjectResponseDto(project);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    public ProjectResponseDto addEmployee(int projectId,int employeeId)  {
        Project project = projectRepo.findById(projectId).orElseThrow(() -> {
            log.error("Project not found!");
            return new ProjectNotFoundException("Project not found!");
        });
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> {
            log.error("Employee not found!");
            return new ProjectNotFoundException("Employee not found!");
        });
        project.getEmployees().add(employee);
        projectRepo.save(project);
        return projectMapper.projectToProjectResponseDto(project);
    }

}
