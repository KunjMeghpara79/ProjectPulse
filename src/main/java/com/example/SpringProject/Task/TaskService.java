package com.example.SpringProject.Task;

import com.example.SpringProject.Employee.EmployeeRepo;
import com.example.SpringProject.Project.ProjectRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    private final EmployeeRepo employeeRepo;
    private final ProjectRepo projectRepo;
    public TaskService(TaskRepo taskRepo, EmployeeRepo employeeRepo, ProjectRepo projectRepo) {
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
        this.projectRepo = projectRepo;
    }

    public TaskStatus checkTaskStatus(Task t){
        return t.getTaskStatus();
    }

    public boolean checkTaskFromList(Task t, List<Task> tasks){
        for (Task task : tasks){
            if(task.getTaskDetails().equals(t.getTaskDetails())) return false;
        }
        return true;
    }

    public TaskResponseDto createTask(TaskDto taskDto){
        boolean employeeExists = employeeRepo.existsById(taskDto.getEmployeeId());
        boolean projectExists = projectRepo.existsById(taskDto.getProjectId());
        if(!employeeExists || !projectExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Task task = new Task(taskDto.getProjectId(),taskDto.getEmployeeId());
        task.setTaskDetails(taskDto.getTaskDetails());
        task.setTaskStatus(TaskStatus.PENDING);
        taskRepo.save(task);
        return new TaskResponseDto(task.getTaskId(),task.getTaskDetails(),task.getTaskStatus(),task.getProjectId(),task.getEmployeeId());
    }
}
