package org.example.Dto.RequestDto.Task;

import lombok.Getter;

@Getter
public class TaskCreateRequestDto {
    private String title;
    private String content;

    public TaskCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
