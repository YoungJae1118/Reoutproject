package org.example.Dto.ResponseDto.Task;

import lombok.Getter;
import org.example.Entity.UserEntity;

@Getter
public class TaskUpdateResponseDto {
    private Long id;
    private String name;
    private String title;
    private String content;

    public TaskUpdateResponseDto(Long id, UserEntity user, String title, String content) {
        this.id = id;
        this.name = user.getName();
        this.title = title;
        this.content = content;
    }

    public TaskUpdateResponseDto() {

    }
}
