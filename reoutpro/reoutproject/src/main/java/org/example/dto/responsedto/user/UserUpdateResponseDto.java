package org.example.Dto.ResponseDto.User;

import lombok.Getter;

@Getter
public class UserUpdateResponseDto {
    private Long id;
    private String email;
    private String password;
    private String name;

    public UserUpdateResponseDto(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
