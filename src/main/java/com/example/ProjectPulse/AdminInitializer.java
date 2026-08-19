package com.example.ProjectPulse;

import com.example.ProjectPulse.Models.Entities.Employee;
import com.example.ProjectPulse.Repositories.EmployeeRepo;
import com.example.ProjectPulse.Enums.EmployeeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    public AdminInitializer(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(!employeeRepo.existsByEmployeeType(EmployeeType.ADMIN)) {
            Employee employee = new Employee();
            employee.setEmployeeName(adminName);
            employee.setEmployeeEmail(adminEmail);
            employee.setPassword(passwordEncoder.encode(adminPassword));
            employee.setEmployeeType(EmployeeType.ADMIN);
            employeeRepo.save(employee);
        }
    }
}
