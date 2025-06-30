package org.example.Dto.ResponseDto.Task;

import lombok.Getter;

@Getter
public class TaskDeleteResponseDto {
    String message;

    public TaskDeleteResponseDto(String message) {
        this.message = message;
    }
}
