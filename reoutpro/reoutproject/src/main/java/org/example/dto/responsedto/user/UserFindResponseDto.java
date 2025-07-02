package org.example.Dto.ResponseDto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindResponseDto {
    private Long id;
    private String email;
    private String name;

    public UserFindResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UserFindResponseDto() {

    }
}
