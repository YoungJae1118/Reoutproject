package org.example.Dto.ResponseDto.Task;

import lombok.Getter;

import java.util.List;

@Getter
public class TaskFindAllResponseDto {
    private List<TasksDto> tasksDtoList;

    public TaskFindAllResponseDto(List<TasksDto> tasksDtoList) {
        this.tasksDtoList = tasksDtoList;
    }
}
