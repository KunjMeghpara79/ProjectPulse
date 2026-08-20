package com.example.ProjectPulse.Controllers;

import com.example.ProjectPulse.Repositories.EmployeeRepo;
import com.example.ProjectPulse.Models.DTOs.LoginRequestDto;
import com.example.ProjectPulse.Security.AuthService;
import com.example.ProjectPulse.Security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final EmployeeRepo employeeRepo;
    private final JwtService jwtService;
    public AuthController(PasswordEncoder passwordEncoder, AuthService authService, EmployeeRepo employeeRepo, JwtService jwtService) {
        this.authService = authService;
        this.employeeRepo = employeeRepo;
        this.jwtService = jwtService;

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.loginValidator(loginRequestDto);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
