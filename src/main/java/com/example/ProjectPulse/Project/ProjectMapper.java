package com.example.ProjectPulse.Project;

import com.example.ProjectPulse.Employee.EmployeeMapper;
import com.example.ProjectPulse.Task.TaskMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "spring" ,uses = {TaskMapper.class, EmployeeMapper.class})

public interface ProjectMapper {
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "task", ignore = true)
    Project projectRequestDtoToProject(ProjectRequestDto projectRequestDto);
    @AfterMapping
    default void initializeCollections(@MappingTarget Project project){
        if(project.getTasks() == null) project.setTask(new ArrayList<>());
        if(project.getEmployees() == null) project.setEmployees(new ArrayList<>());
    }
    ProjectResponseDto projectToProjectResponseDto(Project project);





}
