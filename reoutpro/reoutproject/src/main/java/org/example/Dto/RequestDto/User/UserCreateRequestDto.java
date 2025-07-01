package org.example.Dto.RequestDto.User;

import lombok.Getter;

@Getter
public class UserCreateRequestDto {
    private String email;
    private String password;
    private String name;

    public UserCreateRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
