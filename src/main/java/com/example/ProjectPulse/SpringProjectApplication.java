package com.example.ProjectPulse;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Employee.EmployeeType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner{

    public SpringProjectApplication(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

	private final EmployeeRepo employeeRepo;

	private final PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		if(!employeeRepo.existsByEmployeeType(EmployeeType.ADMIN)){
			Employee employee = new Employee();
			employee.setEmployeeName("Admin");
			employee.setEmployeeEmail("Admin@Niyantras.com");
			employee.setPassword(passwordEncoder.encode("Admin@123"));
			employee.setEmployeeType(EmployeeType.ADMIN);
			employeeRepo.save(employee);
		}
	}
}
