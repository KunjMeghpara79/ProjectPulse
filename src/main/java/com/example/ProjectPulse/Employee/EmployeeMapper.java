package com.example.ProjectPulse.Employee;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    public Employee employeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto);
    public EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee);
}
