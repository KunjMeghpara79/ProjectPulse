package com.example.ProjectPulse.Project;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Employee.EmployeeService;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import com.example.ProjectPulse.Exceptions.ProjectAlreadyExistsException;
import com.example.ProjectPulse.Exceptions.ProjectNotFoundException;
import com.example.ProjectPulse.Task.Task;
import com.example.ProjectPulse.Task.TaskRepo;
import com.example.ProjectPulse.Task.TaskService;
import com.example.ProjectPulse.Task.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public boolean addTask(Project p, Task t){
        TaskStatus taskStatus = taskService.checkTaskStatus(t);
        if(taskStatus == TaskStatus.PENDING){
            List<Task> tasks = p.getTasks();
            if(taskService.checkTaskFromList(t,p.getTasks())) return false;
            else {
                p.getTasks().add(t);
                return true;
            }
        }else return false;
    }

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
        Project project =  projectRepo.findById(id).orElseThrow(() -> {
            log.error("Project not found!");
            return new ProjectNotFoundException("Project not found!");
        });
        return projectMapper.projectToProjectResponseDto(project);
    }

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
