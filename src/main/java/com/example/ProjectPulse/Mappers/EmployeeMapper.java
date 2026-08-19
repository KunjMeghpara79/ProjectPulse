package com.example.ProjectPulse.Mappers;

import com.example.ProjectPulse.DTOs.EmployeeRequestDto;
import com.example.ProjectPulse.DTOs.EmployeeResponseDto;
import com.example.ProjectPulse.Entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    public Employee employeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto);
    public EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee);
}
