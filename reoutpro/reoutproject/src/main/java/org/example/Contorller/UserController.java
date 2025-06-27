package org.example.Contorller;


import org.example.Dto.RequestDto.User.UserCreateRequestDto;
import org.example.Dto.RequestDto.User.UserUpdateRequestDto;
import org.example.Dto.ResponseDto.User.UserCreateResponseDto;
import org.example.Dto.ResponseDto.User.UserDeleteResponseDto;
import org.example.Dto.ResponseDto.User.UserFindResponseDto;
import org.example.Dto.ResponseDto.User.UserUpdateResponseDto;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //유저 생성
    @PostMapping
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        return userService.createUser(userCreateRequestDto);
    }

    //유저 전체 조회
    @GetMapping
    public List<UserFindResponseDto> finAllUser() {
        return userService.findAllUser();
    }

    //유저 단건 조회
    @GetMapping("/{userId}")
    public UserFindResponseDto findbById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    //유저 수정
    @PutMapping("/{userId}")
    public UserUpdateResponseDto updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userId, userUpdateRequestDto);
    }

    //유저 삭제
    @DeleteMapping("/{userId}")
    public UserDeleteResponseDto deleteUser(@PathVariable Long userId) {
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



