package org.example.Dto.ResponseDto.User;

import lombok.Getter;
import org.example.entity.UserEntity;

@Getter
public class UsersDto {
    private Long id;
    private String email;
    private String name;

    public UsersDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UsersDto(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
