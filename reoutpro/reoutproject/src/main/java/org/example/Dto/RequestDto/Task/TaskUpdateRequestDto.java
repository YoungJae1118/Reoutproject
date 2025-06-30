package org.example.Dto.RequestDto.Task;

import lombok.Getter;

@Getter
public class TaskUpdateRequestDto {
    private String title;
    private String content;

    public TaskUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
