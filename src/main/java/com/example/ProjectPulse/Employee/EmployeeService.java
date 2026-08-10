package com.example.ProjectPulse.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public EmployeeResponseDto createEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employeeRepo.save(employee);
        return new EmployeeResponseDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeEmail());
    }

    public EmployeeResponseDto getEmployee(int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new EmployeeResponseDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeEmail());
    }

    /*
    ResponseStatusException is a built-in Java class provided by the Spring Framework
    that allows you to easily map an HTTP status code and a custom text message to a specific error.
    */

    public EmployeeResponseDto updateEmployee(int id, EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(employeeDto.getEmployeeName() != null) employee.setEmployeeName(employeeDto.getEmployeeName());
        if(employeeDto.getEmployeeEmail() != null) employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employeeRepo.save(employee);
        return new EmployeeResponseDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeEmail());
    }

    public boolean deleteEmployeeById(int id)  {
        if(!employeeRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        employeeRepo.deleteById(id);
        return true;
    }
}
