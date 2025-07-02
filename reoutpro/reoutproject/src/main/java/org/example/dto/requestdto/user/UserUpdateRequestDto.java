package org.example.Dto.RequestDto.User;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String email;
    private String password;
    private String name;

    public UserUpdateRequestDto() {

    }
}
