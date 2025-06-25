package org.example.reoutproject.Controller;

import org.example.reoutproject.Service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*
    1. 회원 생성
    2. 회원 조회
    3. 회원 수정
    4. 회원 삭제
    */

}