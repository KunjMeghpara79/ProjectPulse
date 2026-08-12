package com.example.ProjectPulse.Task;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    public Task taskRequestDtoToTask(TaskRequestDto taskRequestDto);
    public TaskResponseDto taskToTaskResponseDto(Task task);
}
