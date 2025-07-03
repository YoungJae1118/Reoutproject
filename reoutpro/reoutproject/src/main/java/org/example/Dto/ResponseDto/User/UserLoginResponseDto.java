package org.example.Dto.ResponseDto.User;

import lombok.Getter;
import org.example.Dto.RequestDto.User.UserLoginRequestDto;

@Getter
public class UserLoginResponseDto {
    private String jwt;
    private String message;

    public UserLoginResponseDto(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }
}
