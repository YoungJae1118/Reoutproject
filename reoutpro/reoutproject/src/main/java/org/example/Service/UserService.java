package org.example.Service;

import org.apache.catalina.User;
import org.example.Entity.UserEntity;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //유저 생성
    public UserEntity creeateUser(UserEntity user) {
        return  userRepository.save(user);
    }
    //유저 전체 조회
    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }
    //유저 필터 조회
    // 이름 조회
    public List<UserEntity> findByNameUser(String name) {
        return userRepository.findByName(name);
    }
    //이메일 조회
    public Optional<UserEntity> findByEmailUser(String email) {
        return userRepository.findByEmail(email);
    }
    //유저 수정
    public UserEntity updateUser(Long id, UserEntity updateuser) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        user.setEmail(updateuser.getEmail());
        user.setPassword(updateuser.getPassword());
        user.setName(updateuser.getName());
        return userRepository.save(user);
    }
    //유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
