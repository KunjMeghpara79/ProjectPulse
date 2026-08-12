package com.example.ProjectPulse.Employee;
import jakarta.persistence.Entity; // CORRECT
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;


    private String employeeName;


    private String employeeEmail;

    public String getEmployeeEmail() {
        return this.employeeEmail;
    }
    public String getEmployeeName() {
        return this.employeeName;
    }
    public int getEmployeeId() {
        return this.employeeId;
    }

    public Employee() {
    }

    public Employee(String employeeName, int employeeId, String employeeEmail) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }


}
