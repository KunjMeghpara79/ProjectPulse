package com.example.ProjectPulse.Project;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Task.Task;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Project {

    @Column(unique = true, nullable = false)
    private String projectName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private List<Employee> employees;

    /*
    When you fetch a Project object from the database,
     Hibernate will populate projectId and projectName but will put Lazy-Loading Proxies (placeholder objects) in place of the employees
      and task lists. The actual database queries to fetch those lists will not run until you explicitly trigger them.
     */

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private List<Task> task;

    public int getProjectId() {
        return projectId;
    }



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
