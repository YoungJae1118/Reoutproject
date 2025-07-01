package org.example.Dto.ResponseDto.Task;

import lombok.Getter;
import org.example.Entity.UserEntity;

@Getter
public class TaskFindResponseDto {
    private Long id;
    private String name;
    private String title;
    private String content;

    public TaskFindResponseDto(Long id, UserEntity userEntity, String title, String content) {
        this.id = id;
        this.name = userEntity.getName();
        this.title = title;
        this.content = content;
    }

    public TaskFindResponseDto() {

    }
}
