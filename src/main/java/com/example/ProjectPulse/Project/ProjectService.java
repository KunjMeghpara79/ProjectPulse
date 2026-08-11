package com.example.ProjectPulse.Project;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Employee.EmployeeResponseDto;
import com.example.ProjectPulse.Employee.EmployeeService;
import com.example.ProjectPulse.Task.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
public class ProjectService {
    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;

    public ProjectService(TaskService taskService, EmployeeService employeeService, ProjectRepo projectRepo, TaskRepo taskRepo) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
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

    public ProjectResponseDto createProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setProjectName(projectDto.projectName());
        project.setEmployees(new ArrayList<>());
        project.setTask(new ArrayList<>());
        projectRepo.save(project);
        List<EmployeeResponseDto> employees = project.getEmployees().stream()
                .map(emp -> new EmployeeResponseDto(emp.getEmployeeId(),emp.getEmployeeName(),emp.getEmployeeEmail()))
                .toList();
        List<TaskResponseDto> tasks = project.getTasks().stream()
                .map(task -> new TaskResponseDto(task.getTaskId(),task.getTaskDetails(),task.getTaskStatus(),task.getProjectId(),task.getEmployeeId()))
                .toList();
        return new ProjectResponseDto(project.getProjectId(),project.getProjectName(),employees,tasks);
    }

    public ProjectResponseDto findProjectById(int id) {
        Project project =  projectRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<EmployeeResponseDto> employees = project.getEmployees().stream()
                .map(emp -> new EmployeeResponseDto(emp.getEmployeeId(),emp.getEmployeeName(),emp.getEmployeeEmail()))
                .toList();
        List<TaskResponseDto> tasks = project.getTasks().stream()
                .map(task -> new TaskResponseDto(task.getTaskId(),task.getTaskDetails(),task.getTaskStatus(),task.getProjectId(),task.getEmployeeId()))
                .toList();
        return new ProjectResponseDto(project.getProjectId(),project.getProjectName(),employees,tasks);
    }

    public ProjectResponseDto addEmployee(int projectId,int employeeId)  {
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        EmployeeResponseDto employee = employeeService.getEmployee(employeeId);
        project.getEmployees().add(new Employee(employee.employeeName(),employee.employeeId(),employee.employeeEmail()));
        projectRepo.save(project);
        List<EmployeeResponseDto> employees = project.getEmployees().stream()
                .map(emp -> new EmployeeResponseDto(emp.getEmployeeId(),emp.getEmployeeName(),emp.getEmployeeEmail()))
                .toList();
        List<TaskResponseDto> tasks = project.getTasks().stream()
                .map(task -> new TaskResponseDto(task.getTaskId(),task.getTaskDetails(),task.getTaskStatus(),task.getProjectId(),task.getEmployeeId()))
                .toList();
        return new ProjectResponseDto(project.getProjectId(),project.getProjectName(),employees,tasks);
    }



    public Project updateProject(int id,String name){
        Project project = projectRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.setProjectName(name);
        projectRepo.save(project);
        return project;
    }
}
