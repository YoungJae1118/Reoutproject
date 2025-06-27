package org.example.Service;

import org.example.Dto.RequestDto.User.UserCreateRequestDto;
import org.example.Dto.RequestDto.User.UserUpdateRequestDto;
import org.example.Dto.ResponseDto.User.UserCreateResponseDto;
import org.example.Dto.ResponseDto.User.UserDeleteResponseDto;
import org.example.Dto.ResponseDto.User.UserFindResponseDto;
import org.example.Dto.ResponseDto.User.UserUpdateResponseDto;
import org.example.Entity.UserEntity;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //유저 생성
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        //1. 데이터 준비
        String email = userCreateRequestDto.getEmail();
        String password = userCreateRequestDto.getPassword();
        String name = userCreateRequestDto.getName();
        //2. 엔티티 만들기
        UserEntity userEntity = new UserEntity(email, password, name);
        //3. 저장
        UserEntity saveUser = userRepository.save(userEntity);
        //4. responseDto 만들기
        UserCreateResponseDto userCreateResponseDto = new UserCreateResponseDto(saveUser);
        //5.responseDto 반환
        return userCreateResponseDto;
    }

    //유저 전체 조회
    public List<UserFindResponseDto> findAllUser() {
        // 1.데이터 준비하기
        List<UserEntity> userList;
        List<UserFindResponseDto> userFindResponseDtoList = new ArrayList<>();
        // 2. 검증로직 작성 필요시

        // 3. 리스트 조회
        userList = userRepository.findAll();
        // 4. responseDto 만들기
        for (UserEntity user : userList) {
            UserFindResponseDto userFindResponseDto = new UserFindResponseDto(
                    user.getId(), user.getEmail(), user.getName()
            );
            userFindResponseDtoList.add(userFindResponseDto);
        }
        // 5. responserDto 반환
        return userFindResponseDtoList;
    }


    //유저 단건 조회
    public UserFindResponseDto findById(Long id) {
        // 1. 데이터 준비하기
        Long userId = id;
        // 2.검증로직 작성 필요시 3. 리스트 조회
        UserEntity userList = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("입력하신 Id에 해당하는 User가 없습니다."));
        // 4. responseDto 만들기
        UserFindResponseDto userFindResponseDto = new UserFindResponseDto(userList.getId(), userList.getEmail(), userList.getName());
        // 5. responseDto 반환
        return userFindResponseDto;
    }

    //유저 수정
    public UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto updateUser) {
        // 1. 데이터 준비하기
        String newEmail = updateUser.getEmail();
        String newPassword = updateUser.getPassword();
        String newName = updateUser.getPassword();
        // 2. 검증로직 작성 필요시  3. 조회
        UserEntity oldUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 유저 정보가 없습니다"));
        // 4.업데이트
        UserEntity newUser = new UserEntity(id, newEmail, newPassword, newName);
        userRepository.save(newUser);
        // 5. responseDto 만들기
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto(id, newEmail, newPassword, newName);
        // 6.responseDto 반환
        return userUpdateResponseDto;
    }

    public UserDeleteResponseDto deleteUser(Long id) {
        // 1. 데이터 준비하기

        // 2. 검증로직 작성 3. 조회
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 유저가 존재하지 않습니다."));
        // 4. 삭제
        userRepository.deleteById(id);
        // 5. responseDto 생성
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto("삭제가 완료되었습니다.");
        // 6. responseDto 반환
        return userDeleteResponseDto;
    }
}


// ArrayList라는 목록형으로 데이터를 한번에 다룰 수 있는 자료구조 객체를 선언과 초기화

// 5년~ 10년 정도 운영하게 되면 추가적인 로직이나 요구사항 관련된 코드들이 중간 중간 적절한 순서에 따라 추가될 수 있는데,

//        List<UserFindResponseDto> results2 = users.stream()
//                .map(user -> {
//                    UserFindResponseDto userFindResponseDto = new UserFindResponseDto();
//                    userFindResponseDto.setId(user.getId());
//                    userFindResponseDto.setEmail(user.getEmail());
//                    userFindResponseDto.setName(user.getName());
//                    return userFindResponseDto;
//                }).toList();
// (서동우 튜터님 06/26)위에 방법 : users처럼 총량 -> 큰 값의 데이터를 불러와야 한다는 단점 반면 stream은 총 데이터를 불러오진 않으니 서버 부담이 적음!

//유저 필터 조회
// 이름 조회(동명이인 있으면 여러명 나와야 함 -> 리스트로 뽑자)
/*
    public List<UserFindResponseDto> findByNameUser(String name) {
        List<UserEntity> users = userRepository.findByName(name);
        List<UserFindResponseDto> result = new ArrayList<>();

        for (UserEntity user : users) {
            UserFindResponseDto userFindResponseDto = new UserFindResponseDto();
            userFindResponseDto.setId(user.getId());
            userFindResponseDto.setEmail(user.getEmail());
            userFindResponseDto.setName(user.getName());
            result.add(userFindResponseDto);
        }

//        List<UserFindResponseDto> results = users.stream()
//                .map(user -> {
//                            UserFindResponseDto userFindResponseDto = new UserFindResponseDto();
//                            userFindResponseDto.setId(user.getId());
//                            userFindResponseDto.setEmail(user.getEmail());
//                            userFindResponseDto.setName(user.getName());
//                            return userFindResponseDto;
//                        }
//                ).toList();  (06/26 최용석 튜터님)

        return result;
    }
*/
//    public void nullProblemExample() {
//        // Null이 아님 : new 키워드를 이용해서 객체가 생성된 객체가 메모리 공간에 위치
//        UserFindResponseDto userFindResponseDto1 = new UserFindResponseDto();
//
//        // Null : 선언만하고 생성자를 호출하지 않아서 초기화 과정이 없었으므로, 해당 객체는 메모리 공간에 위치하지 못했어요 아직
//        UserFindResponseDto userFindResponseDto; // Null
//        userFindResponseDto.getId(); // null -> Java에서는 null인 객체에 내부에 접근하는 경우 NullPointException이 발생해요
//
//        Optional<UserEntity> optionalUser = userRepository.findByEmail("email@email.com");
//        UserEntity userEntity = optionalUser.get(); // null
//        userEntity.getId(); // null.getId(); -> NullPointException
//    }
/*
    //이메일 조회
    public UserFindResponseDto findByEmailUser(String email) {
        // Java의 Optional<UserEntity> : userRepository.findByEmail(email)의 결과 값이 항상 존재함을 보장할 수 없음
        // 있는 경우 : Optional<UserEntity> optionalUser: optionalUser.get(); //으로 조회된 UserEntity에 접근 가능
        // 없는 경우 : Optional<UserEntity> optionalUser: optionalUser.get(); //으로 존재하지 않는 UserEntity에 접근 시 예외 발생
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("요청에 해당하는 사용자가 존재하지 않습니다."));

        // 밑에 라인들은 항상 조회 성공한 userEntity에 대한 추가 작업을 안전하게 실행할 수 있는 환경을 구성
        UserFindResponseDto userFindResponseDto = new UserFindResponseDto();
        userFindResponseDto.setId(userEntity.getId());
        userFindResponseDto.setEmail((userEntity.getEmail()));
        userFindResponseDto.setName(userEntity.getName());
        return userFindResponseDto;
    }
*/
