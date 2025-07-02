package org.example.Dto.ResponseDto.User;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.UserEntity;

@Getter
@Setter
public class UserCreateResponseDto {
    private Long id;
    private String email;
    private String name;

    public UserCreateResponseDto(UserEntity saveUser) {
        this.id = saveUser.getId();
        this.email = saveUser.getEmail();
        this.name = saveUser.getName();
    }
}
