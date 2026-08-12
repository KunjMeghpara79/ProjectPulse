package com.example.ProjectPulse.Employee;

import com.example.ProjectPulse.Task.TaskRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    private final EmployeeMapper employeeMapper;
    private final TaskRepo taskRepo;

    public EmployeeService(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, TaskRepo taskRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.taskRepo = taskRepo;
    }

    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = employeeMapper.employeeRequestDtoToEmployee(employeeRequestDto);
        boolean employeeExists = employeeRepo.existsByEmployeeEmail(employeeRequestDto.employeeEmail());
        if (employeeExists) throw new ResponseStatusException(HttpStatus.CONFLICT);
        employeeRepo.save(employee);
        return employeeMapper.employeeToEmployeeResponseDto(employee);
    }

    public EmployeeResponseDto getEmployee(int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return employeeMapper.employeeToEmployeeResponseDto(employee);
    }

    /*
    ResponseStatusException is a built-in Java class provided by the Spring Framework
    that allows you to easily map an HTTP status code and a custom text message to a specific error.
    */

    public EmployeeResponseDto updateEmployee(int id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(employeeRequestDto.employeeName() != null) employee.setEmployeeName(employeeRequestDto.employeeName());
        if(employeeRequestDto.employeeEmail() != null) employee.setEmployeeEmail(employeeRequestDto.employeeEmail());
        employeeRepo.save(employee);
        return employeeMapper.employeeToEmployeeResponseDto(employee);
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
