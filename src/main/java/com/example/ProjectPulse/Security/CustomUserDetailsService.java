package com.example.ProjectPulse.Security;

import com.example.ProjectPulse.Models.Entities.Employee;
import com.example.ProjectPulse.Repositories.EmployeeRepo;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepo employeeRepo;

    public CustomUserDetailsService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByEmployeeEmail(username).orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(employee.getEmployeeEmail())
                .password(employee.getPassword())
                .authorities("ROLE_" + employee.getEmployeeType().name())
                /*
                Spring's userdetails class contains authorities so it can contain both roles and permissions
                so to differentiate these two we have historical convention of adding "ROLE_" before role.
                spring security will automatically add "ROLE_" while executing role based access endpoints or methods.
                */
                .build();
    }

}
