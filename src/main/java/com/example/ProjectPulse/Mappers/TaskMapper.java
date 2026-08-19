package com.example.ProjectPulse.Mappers;


import com.example.ProjectPulse.DTOs.TaskRequestDto;
import com.example.ProjectPulse.DTOs.TaskResponseDto;
import com.example.ProjectPulse.Entities.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    public Task taskRequestDtoToTask(TaskRequestDto taskRequestDto);
    public TaskResponseDto taskToTaskResponseDto(Task task);
}
