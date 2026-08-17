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


    private final PasswordEncoder passwordEncoder;

    private final AuthService authService;


    private final EmployeeRepo employeeRepo;
    private final JwtService jwtService;
    public AuthController(PasswordEncoder passwordEncoder, AuthService authService, EmployeeRepo employeeRepo, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
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
