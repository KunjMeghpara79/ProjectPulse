package com.example.ProjectPulse.Controllers;


import com.example.ProjectPulse.DTOs.TaskRequestDto;
import com.example.ProjectPulse.DTOs.TaskResponseDto;
import com.example.ProjectPulse.Entities.Task;
import com.example.ProjectPulse.Services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto){
        TaskResponseDto task = taskService.createTask(taskRequestDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam int page, @RequestParam int size){
        Page<Task> p = taskService.getTasks(page, size);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable int taskId){
        taskService.delteTask(taskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
