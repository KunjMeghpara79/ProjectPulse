package com.example.ProjectPulse.Task;

import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Project.ProjectRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto){
        boolean employeeExists = employeeRepo.existsById(taskRequestDto.employeeId());
        boolean projectExists = projectRepo.existsById(taskRequestDto.projectId());
        if (!employeeExists || !projectExists) throw new ResponseStatusException(HttpStatus.CONFLICT);
        Task task = new Task();
        task.setEmployeeId(taskRequestDto.employeeId());
        task.setProjectId(taskRequestDto.projectId());
        task.setTaskDetails(taskRequestDto.taskDetails());
        task.setTaskStatus(TaskStatus.PENDING);
        taskRepo.save(task);
        projectRepo.findById(task.getProjectId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getTasks().add(task);
        return new TaskResponseDto(task.getTaskId(),task.getTaskDetails(),task.getTaskStatus(),task.getEmployeeId(),task.getProjectId());
    }

    public Page<Task> getTasks(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return taskRepo.findAll(pageable);
    }

    public void delteTask(int id){
        Task task = taskRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taskRepo.deleteById(id);
    }
}
