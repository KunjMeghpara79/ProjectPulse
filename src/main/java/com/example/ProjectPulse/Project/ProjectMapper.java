package com.example.ProjectPulse.Project;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper
public interface ProjectMapper {
    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "task", ignore = true)
    Project projectRequestDtoToProject(ProjectRequestDto projectRequestDto);
    ProjectResponseDto projectToProjectResponseDto(Project project);

    @AfterMapping
    default void initializeCollections(@MappingTarget Project project){
        if(project.getEmployees() == null){
            project.setEmployees(new ArrayList<>());
        }
        if(project.getTasks() == null){
            project.setTask(new ArrayList<>());
        }
    }

}
