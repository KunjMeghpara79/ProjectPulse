package com.example.ProjectPulse.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    public boolean existsByEmployeeEmail(String email);
    public boolean existsByEmployeeType(EmployeeType employeeType);
    public Employee findByEmployeeEmail(String email);
}
