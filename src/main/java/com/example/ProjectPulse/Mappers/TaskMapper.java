package com.example.ProjectPulse.Mappers;


import com.example.ProjectPulse.Models.DTOs.TaskRequestDto;
import com.example.ProjectPulse.Models.DTOs.TaskResponseDto;
import com.example.ProjectPulse.Models.Entities.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    public Task taskRequestDtoToTask(TaskRequestDto taskRequestDto);
    public TaskResponseDto taskToTaskResponseDto(Task task);
}
