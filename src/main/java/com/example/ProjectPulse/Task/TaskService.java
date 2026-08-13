package com.example.ProjectPulse.Task;

import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import com.example.ProjectPulse.Exceptions.ProjectNotFoundException;
import com.example.ProjectPulse.Exceptions.TaskNotFoundException;
import com.example.ProjectPulse.Project.ProjectRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final TaskRepo taskRepo;
    private final EmployeeRepo employeeRepo;
    private final ProjectRepo projectRepo;
    private final TaskMapper taskMapper;
   // private final Logger log = LoggerFactory.getLogger(TaskService.class);
    public TaskService(TaskRepo taskRepo, EmployeeRepo employeeRepo, ProjectRepo projectRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
        this.projectRepo = projectRepo;
        this.taskMapper = taskMapper;
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

    @Transactional
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto){
        boolean employeeExists = employeeRepo.existsById(taskRequestDto.employeeId());
        boolean projectExists = projectRepo.existsById(taskRequestDto.projectId());
        if(!projectExists){
            log.error("Project not found!");
            throw new ProjectNotFoundException("Project not found !");
        }
        if(!employeeExists){
            log.error("Employee not found!");
            throw new EmployeeNotFoundException("Project not found !");
        }
        Task task = taskMapper.taskRequestDtoToTask(taskRequestDto);
        task.setTaskStatus(TaskStatus.PENDING);
        taskRepo.save(task);
        projectRepo.findById(task.getProjectId()).orElseThrow(() -> {
            log.error("Project not found!");
            return new ProjectNotFoundException("Project not found !");
        }).getTasks().add(task);
        return taskMapper.taskToTaskResponseDto(task);
    }

    public Page<Task> getTasks(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return taskRepo.findAll(pageable);
    }

    public void delteTask(int id){
        Task task = taskRepo.findById(id).orElseThrow(() -> {
            log.error("Task not found!");
           return new TaskNotFoundException("Project not found !");
        });
        taskRepo.deleteById(id);
    }
}
