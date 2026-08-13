package com.example.ProjectPulse.Employee;

import com.example.ProjectPulse.Exceptions.EmployeeAlreadyExistsException;
import com.example.ProjectPulse.Exceptions.EmployeeNotFoundException;
import com.example.ProjectPulse.Task.TaskRepo;
import com.example.ProjectPulse.Task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    private final EmployeeMapper employeeMapper;
    private final TaskRepo taskRepo;
   // private final Logger log = LoggerFactory.getLogger(TaskService.class);
    public EmployeeService(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, TaskRepo taskRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.taskRepo = taskRepo;
    }

    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = employeeMapper.employeeRequestDtoToEmployee(employeeRequestDto);
        boolean employeeExists = employeeRepo.existsByEmployeeEmail(employeeRequestDto.employeeEmail());
        if (employeeExists) {
            log.error("Employee Already exists!");
            throw new EmployeeAlreadyExistsException("Employee Already exists!");
        }
        employeeRepo.save(employee);
        return employeeMapper.employeeToEmployeeResponseDto(employee);
    }

    public EmployeeResponseDto getEmployee(int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> {
            log.error("Employee not found!");
            return new EmployeeNotFoundException("Employee not found!");
        });
        return employeeMapper.employeeToEmployeeResponseDto(employee);
    }

    /*
    ResponseStatusException is a built-in Java class provided by the Spring Framework
    that allows you to easily map an HTTP status code and a custom text message to a specific error.
    */

    public EmployeeResponseDto updateEmployee(int id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() ->{
            log.error("Employee not found!");
            return new EmployeeNotFoundException("Employee not found!");
        });
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
