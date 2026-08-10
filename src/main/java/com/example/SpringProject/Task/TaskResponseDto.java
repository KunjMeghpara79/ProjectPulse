package com.example.SpringProject.Task;

public record TaskResponseDto( int taskId,
         String taskDetails,
         TaskStatus taskStatus,
        int projectId,
         int employeeId) {
}
