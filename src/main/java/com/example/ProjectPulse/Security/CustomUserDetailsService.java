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
                /*
                We add other authorities because a role alone may not give enough detailed control.

                For example, two users can both be EMPLOYEE, but:

                Employee 1 → can VIEW_PROJECT

                Employee 2 → can VIEW_PROJECT and UPDATE_PROJECT

                 Both have the same role, but different permissions/authorities.
                 */
                .build();
    }

}
