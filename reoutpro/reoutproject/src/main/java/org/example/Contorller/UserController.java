package org.example.Contorller;

import org.example.Entity.UserEntity;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //유저 생성
    @PostMapping("/register")
    public UserEntity createUser(UserEntity user) {
        return userService.creeateUser(user);
    }
    //유저 전체 조회
    @GetMapping("/find/all")
    public List<UserEntity> finAllUser() {
        return userService.findAllUser();
    }
    //유저 단건 조회(이름, 이메일)
    @GetMapping("/find")
    public List<UserEntity> findByNameUser(@RequestParam String name) {
        return userService.findByNameUser(name);
    }
    @GetMapping("/find")
    public Optional<UserEntity> findByEmailUser(@RequestParam String email) {
        return userService.findByEmailUser(email);
    }
    //유저 수정
    @PutMapping("/update")
    public UserEntity updateUser(@RequestParam Long id, @RequestBody UserEntity user) {
        return userService.updateUser(id,user);
    }
    //유저 삭제
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}


