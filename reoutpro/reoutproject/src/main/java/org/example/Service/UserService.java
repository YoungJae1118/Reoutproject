package org.example.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.buf.UEncoder;
import org.example.Dto.RequestDto.User.UserCreateRequestDto;
import org.example.Dto.RequestDto.User.UserLoginRequestDto;
import org.example.Dto.RequestDto.User.UserUpdateRequestDto;
import org.example.Dto.ResponseDto.User.*;
import org.example.Entity.UserEntity;
import org.example.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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
        //비밀번호 검증 (길이 : .length() / 아무 문자 : .* / 대문자 : [A-Z] / 소문자 : [a-z] / 숫자 : \\d / 특수기호 : [특수기호 넣고 싶은거])
        //길이 8~12자
        if (password.length()<8 || password.length()>12) {
            throw new IllegalArgumentException("비밀번호의 길이는 8~12자여야 합니다.");
        }
        //영어 소문자 포함
        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("비밀번호에 소문자는 포함되어야 합니다.");
        }
        //영어 숫자 포함
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("비밀번호에는 숫자가 포함되어야 합니다");
        }
        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        //2. 엔티티 만들기
        UserEntity userEntity = new UserEntity(email, encodedPassword, name);
        //3. 저장
        UserEntity saveUser = userRepository.save(userEntity);
        //4. responseDto 만들기
        UserCreateResponseDto userCreateResponseDto = new UserCreateResponseDto(saveUser);
        //5.responseDto 반환
        return userCreateResponseDto;
    }

    //로그인
    @Transactional
    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        //데이터 준비
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();
        //유저 조회
        Optional<UserEntity> findEmailUser = userRepository.findByEmail(email);
        if (findEmailUser.isEmpty()) {
            throw new IllegalArgumentException("이메일이 일치하는 유저가 존재하지 않습니다");
        }
        //비밀번호 검증
        //비밀번호는 암호화 할때마다 다른 값이 나온다.... 그리고 optional객체는 .get을 먼저 써서 실체를 불러오고 나서 get으로 얻기
        if (!passwordEncoder.matches(password,findEmailUser.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하는 유저가 존재하지 않습니다");
        }
        //Token 생성
        String jwt = jwtService.createJwt(findEmailUser.get().getId());
        //Dto 생성
        UserLoginResponseDto userLoginResponseDto = new  UserLoginResponseDto(jwt, "로그인에 성공하였습니다.");
        //Dto 반환
        return userLoginResponseDto;
    }

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
    public UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto updateUser, HttpServletRequest httpServletRequest) {
        // 1. 데이터 준비하기
        String newEmail = updateUser.getEmail();
        String newPassword = updateUser.getPassword();
        String newName = updateUser.getName();
        // 2. 검증로직 작성 필요시  3. 조회
        UserEntity oldUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 유저 정보가 없습니다"));
        //인증인가
        //헤더 뽑기
        String authHeader = httpServletRequest.getHeader("Auth");
        //토큰 조건 부합 확인
        if (!authHeader.startsWith("Bearer")) {
            throw new IllegalArgumentException("Bearer 토큰이 인식되지 않았습니다");
        }
        String token = authHeader.substring(7); //띄어쓰기도 생각 Bearer token
        //토큰이 일치하는지 확인

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
