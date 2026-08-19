package com.example.ProjectPulse.Models.Entities;
import com.example.ProjectPulse.Enums.EmployeeType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String employeeName;

    @Column(unique = true, nullable = false)
    private String employeeEmail;

    private String password;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    // mappedBy employees means the relationship is handles in Project class and it contains variable named employee

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    public Employee() {
    }
    public Employee(String employeeName, int employeeId, String employeeEmail,String password) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public String getEmployeeEmail() {
        return this.employeeEmail;
    }
    public String getEmployeeName() {
        return this.employeeName;
    }
    public int getEmployeeId() {
        return this.employeeId;
    }
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
