package com.example.ProjectPulse.Entities;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Project {

    @Column(unique = true, nullable = false)
    private String projectName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>(); // Set prevents query duplicates

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

     /*
      When you fetch a Project object from the database,
      Hibernate will populate projectId and projectName but will put Lazy-Loading Proxies (placeholder objects) in place of the employees
      and task lists. The actual database queries to fetch those lists will not run until you explicitly trigger them.
     */

    public int getProjectId() {
        return projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void setTask(List<Task> task) {
        this.tasks = task;
    }

}
