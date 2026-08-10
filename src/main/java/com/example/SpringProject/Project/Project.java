package com.example.SpringProject.Project;

import com.example.SpringProject.Employee.Employee;
import com.example.SpringProject.Task.Task;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Project {

    @Column(unique = true, nullable = false)
    private String projectName;

    public int getProjectId() {
        return projectId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private List<Task> task;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Task> getTasks() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }






}
