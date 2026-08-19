package com.example.ProjectPulse.Repositories;

import com.example.ProjectPulse.Enums.EmployeeType;
import com.example.ProjectPulse.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    public boolean existsByEmployeeEmail(String email);
    public boolean existsByEmployeeType(EmployeeType employeeType);
    public Employee findByEmployeeEmail(String email);
}
