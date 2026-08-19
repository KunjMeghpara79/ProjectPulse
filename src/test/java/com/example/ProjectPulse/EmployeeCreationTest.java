package com.example.ProjectPulse;

import com.example.ProjectPulse.DTOs.EmployeeRequestDto;
import com.example.ProjectPulse.Enums.EmployeeType;
import com.example.ProjectPulse.Services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeCreationTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void createUserTest(){
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("Kunj","kunj@gmail.com","password", EmployeeType.ASSOCIATE);
        employeeService.createEmployee(employeeRequestDto);
    }
}
