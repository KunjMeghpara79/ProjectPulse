package com.example.ProjectPulse.Task;


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
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskDto taskDto){
        TaskResponseDto task = taskService.createTask(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam int page,@RequestParam int size){
        Page<Task> p = taskService.getTasks(page, size);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable int taskId){
        taskService.delteTask(taskId);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
