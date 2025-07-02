package org.example.Dto.ResponseDto.User;

import lombok.Getter;

@Getter
public class UserDeleteResponseDto {
    String message;

    public UserDeleteResponseDto(String message) {
        this.message = message;
    }
}
