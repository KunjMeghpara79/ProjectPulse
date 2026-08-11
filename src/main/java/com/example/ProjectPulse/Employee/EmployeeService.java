package com.example.ProjectPulse.Employee;

import com.example.ProjectPulse.Task.TaskRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    private final TaskRepo taskRepo;

    public EmployeeService(EmployeeRepo employeeRepo, TaskRepo taskRepo) {
        this.employeeRepo = employeeRepo;
        this.taskRepo = taskRepo;
    }


    public EmployeeResponseDto createEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDto.employeeName());
        employee.setEmployeeEmail(employeeDto.employeeEmail());
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
        if(employeeDto.employeeName() != null) employee.setEmployeeName(employeeDto.employeeName());
        if(employeeDto.employeeEmail() != null) employee.setEmployeeEmail(employeeDto.employeeEmail());
        employeeRepo.save(employee);
        return new EmployeeResponseDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeEmail());
    }

    public boolean deleteEmployeeById(int id)  {
        if(!employeeRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taskRepo.deleteTasks(id);
        employeeRepo.deleteById(id);
        return true;
    }
}
