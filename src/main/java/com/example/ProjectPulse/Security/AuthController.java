package com.example.ProjectPulse.Security;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Employee.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final EmployeeRepo employeeRepo;
    private final JwtService jwtService;
    public AuthController(EmployeeRepo employeeRepo, JwtService jwtService) {
        this.employeeRepo = employeeRepo;
        this.jwtService = jwtService;

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        // 1. Fetch user from DB using the email
       Employee employee = employeeRepo.findByEmployeeEmail(loginRequestDto.email());


        // 2. Directly verify the incoming password against the hashed DB password
        if (!passwordEncoder.matches(loginRequestDto.password(), employee.getPassword())) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        // 3. Generate token if credentials match
        String token = jwtService.generateToken(loginRequestDto.email());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
