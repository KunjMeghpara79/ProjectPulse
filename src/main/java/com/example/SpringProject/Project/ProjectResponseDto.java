package com.example.SpringProject.Project;

import com.example.SpringProject.Employee.Employee;
import com.example.SpringProject.Employee.EmployeeResponseDto;
import com.example.SpringProject.Task.Task;
import com.example.SpringProject.Task.TaskResponseDto;

import java.util.List;

public record ProjectResponseDto(int projectId, String projectName, List<EmployeeResponseDto> employees, List<TaskResponseDto> tasks) {

}
