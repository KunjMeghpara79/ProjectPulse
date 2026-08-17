package com.example.ProjectPulse.Security;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Employee.LoginRequestDto;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import com.example.ProjectPulse.Exceptions.InvalidUsernameOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public String loginValidator(LoginRequestDto loginRequestDto){
        Employee employee = employeeRepo.findByEmployeeEmail(loginRequestDto.email());
        if (employee == null) throw new EmployeeNotFoundException("Employee not found!");

        if (!passwordEncoder.matches(loginRequestDto.password(), employee.getPassword())) {
            throw new InvalidUsernameOrPasswordException("Invalid User name or password!");
        }

        // 3. Generate token if credentials match
        String token = jwtService.generateToken(loginRequestDto.email());
        return token;
    }
}
