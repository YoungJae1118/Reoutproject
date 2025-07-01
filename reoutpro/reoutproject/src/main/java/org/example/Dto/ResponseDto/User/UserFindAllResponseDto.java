package org.example.Dto.ResponseDto.User;


import lombok.Getter;

import java.util.List;

@Getter
public class UserFindAllResponseDto {
    private List<UsersDto> userDtoLists;

    public UserFindAllResponseDto(List<UsersDto> usersDtoList) {
        this.userDtoLists = usersDtoList;
    }

//    public UserFindAllResponseDto(List<UserEntity> userDtoList) {
//        this.userDtoLists = userDtoList;
//    }
}
