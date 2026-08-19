package com.example.ProjectPulse.Entities;


import com.example.ProjectPulse.Enums.TaskStatus;
import jakarta.persistence.*;

@Entity
public class Task {

    public int getTaskId() {
        return taskId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String taskDetails;
    private TaskStatus taskStatus;
    private int projectId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private int employeeId;

    public String getTaskDetails() {
        return taskDetails;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }



}
