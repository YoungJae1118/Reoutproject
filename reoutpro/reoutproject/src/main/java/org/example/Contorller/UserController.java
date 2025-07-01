package org.example.Contorller;

import org.example.Dto.RequestDto.User.UserCreateRequestDto;
import org.example.Dto.RequestDto.User.UserUpdateRequestDto;
import org.example.Dto.ResponseDto.User.*;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //유저 생성
    @PostMapping
    public UserCreateResponseDto createUserControl(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        return userService.createUser(userCreateRequestDto);
    }

    //유저 전체 조회
    @GetMapping
    public UserFindAllResponseDto finAllUserControl() {
        return userService.findAllUser();
    }

    //유저 단건 조회
    @GetMapping("/{userId}")
    public UserFindResponseDto findbByIdControl(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    //유저 수정
    @PutMapping("/{userId}")
    public UserUpdateResponseDto updateUserControl(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userId, userUpdateRequestDto);
    }

    //유저 삭제
    @DeleteMapping("/{userId}")
    public UserDeleteResponseDto deleteUserControl(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}

//    //유저 필터 조회(이름, 이메일)  XXXXXX
//    @GetMapping("/find")
//    public List<UserFindResponseDto> findByNameUser(@RequestParam String name) {
//        return userService.findByNameUser(name);
//    }
//
//    @GetMapping("/find")
//    public Optional<UserEntity> findByEmailUser(@RequestParam String email) {
//
//    }



