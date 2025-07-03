package org.example.Dto.RequestDto.User;

import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    private String email;
    private String password;

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
