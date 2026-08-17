package com.example.ProjectPulse.Security;

import com.example.ProjectPulse.Employee.Employee;
import com.example.ProjectPulse.Employee.EmployeeRepo;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
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
        Employee employee = employeeRepo.findByEmployeeEmail(username);
        if(employee == null) throw new EmployeeNotFoundException("Employee not found!");
        return org.springframework.security.core.userdetails.User.builder()
                .username(employee.getEmployeeEmail())
                .password(employee.getPassword())
                .authorities("ROLE_" + employee.getEmployeeType().name())
                .build();
    }

}
