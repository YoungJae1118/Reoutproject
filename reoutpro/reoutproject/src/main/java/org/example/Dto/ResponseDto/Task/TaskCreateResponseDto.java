package org.example.Dto.ResponseDto.Task;

import lombok.Getter;

@Getter
public class TaskCreateResponseDto {
    private Long id;
    private String userNaem;
    private String title;
    private String content;

    public TaskCreateResponseDto(Long id, String userName, String title, String content) {
        this.id = id;
        this.userNaem = userName;
        this.title = title;
        this.content = content;
    }
}
