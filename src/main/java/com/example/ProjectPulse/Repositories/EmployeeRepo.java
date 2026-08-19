package com.example.ProjectPulse.Repositories;

import com.example.ProjectPulse.Enums.EmployeeType;
import com.example.ProjectPulse.Models.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    public boolean existsByEmployeeEmail(String email);
    public boolean existsByEmployeeType(EmployeeType employeeType);
    public Optional<Employee> findByEmployeeEmail(String email);
     /*
     Optional<Employee> → The method may return an Employee, or no Employee if not found.
     findByEmployeeEmail → Spring Data JPA automatically creates the query based on the method name.
     String email → The email value that will be used to search for the Employee.
     If no Employee is found → Optional.empty() is returned.
      */
}
