package org.example.Service;

import jakarta.transaction.Transactional;
import org.apache.tomcat.util.buf.UEncoder;
import org.example.Dto.RequestDto.User.UserCreateRequestDto;
import org.example.Dto.RequestDto.User.UserLoginRequestDto;
import org.example.Dto.RequestDto.User.UserUpdateRequestDto;
import org.example.Dto.ResponseDto.User.*;
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
    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        //1. 데이터 준비
        String email = userCreateRequestDto.getEmail();
        String password = userCreateRequestDto.getPassword();
        String name = userCreateRequestDto.getName();
        //이메일 중복 확인
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("동일한 이메일이 존재합니다");
        }
//        //비밀번호 암호화
//        String encodedPassword =
        //2. 엔티티 만들기
        UserEntity userEntity = new UserEntity(email, password, name);
        //3. 저장
        UserEntity saveUser = userRepository.save(userEntity);
        //4. responseDto 만들기
        UserCreateResponseDto userCreateResponseDto = new UserCreateResponseDto(saveUser);
        //5.responseDto 반환
        return userCreateResponseDto;
    }
/*
    //로그인
    //@Transactional
    public String loginUser(UserLoginRequestDto userLoginRequestDto) {
        //Data
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();

        //find repository
        userRepository
    }
*/
    //유저 전체 조회
    @Transactional
    public UserFindAllResponseDto findAllUser() {
        // 2. 검증로직 작성 필요시

        // 3. 리스트 조회
        List<UserEntity> userList = userRepository.findAll();
        // 4. responseDto 만들기
        List<UsersDto> usersDtoList = userList.stream()
                .map(user -> new UsersDto(user.getId(), user.getEmail(), user.getName()))
                .toList();
        UserFindAllResponseDto userFindAllResponseDto = new UserFindAllResponseDto(usersDtoList);
        // 5. responserDto 반환
        return userFindAllResponseDto;
    }

    //유저 단건 조회
    @Transactional
    public UserFindResponseDto findById(Long userId) {
        // 2.검증로직 작성 필요시 3. 리스트 조회
        UserEntity userList = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("입력하신 Id에 해당하는 User가 없습니다."));
        // 4. responseDto 만들기
        UserFindResponseDto userFindResponseDto = new UserFindResponseDto(userList.getId(), userList.getEmail(), userList.getName());
        // 5. responseDto 반환
        return userFindResponseDto;
    }

    //유저 수정
    @Transactional
    public UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto updateUser) {
        // 1. 데이터 준비하기
        String newEmail = updateUser.getEmail();
        String newPassword = updateUser.getPassword();
        String newName = updateUser.getName();
        // 2. 검증로직 작성 필요시  3. 조회
        UserEntity oldUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 유저 정보가 없습니다"));
        // 4.업데이트
        oldUser.updateUser(newEmail, newPassword, newName);
        userRepository.save(oldUser);
        // 5. responseDto 만들기
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto(id, newEmail, newPassword, newName);
        // 6.responseDto 반환
        return userUpdateResponseDto;
    }

    @Transactional
    public UserDeleteResponseDto deleteUser(Long userId) {
        // 1. 데이터 준비하기

        // 2. 검증로직 작성 3. 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 유저가 존재하지 않습니다."));
        // 4. 삭제
        userRepository.deleteById(userId);
        // 5. responseDto 생성
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto("삭제가 완료되었습니다.");
        // 6. responseDto 반환
        return userDeleteResponseDto;
    }
}
