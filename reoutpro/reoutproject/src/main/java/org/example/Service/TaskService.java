package org.example.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.example.Dto.RequestDto.Task.TaskCreateRequestDto;
import org.example.Dto.RequestDto.Task.TaskUpdateRequestDto;
import org.example.Dto.ResponseDto.Task.*;
import org.example.Entity.TaskEntity;
import org.example.Entity.UserEntity;
import org.example.Repository.TaskRepository;
import org.example.Repository.UserRepository;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, JwtService jwtService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    //태스크 생성
    @Transactional
    public TaskCreateResponseDto createTask(Long userId, TaskCreateRequestDto taskCreateRequestDto, HttpServletRequest httpServletRequest) {
        // 1. 데이터 준비
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 이용자가 없습니다."));
        String title = taskCreateRequestDto.getTitle();
        String content = taskCreateRequestDto.getContent();
        String userName = user.getName();
        String authHeader = httpServletRequest.getHeader("Authorization");
        // 2. 검증로직
        if(!authHeader.startsWith("Bearer")) {
            throw new IllegalArgumentException("토큰을 인식하지 못했습니다.");
        }
        String token = authHeader.substring(7);
        Long idInToken = jwtService.parserJwtForId(token);
        if(!userId.equals(idInToken)) {
            throw new IllegalArgumentException("로그인한 계정 정보와 입력하신 userId가 일치하지 않습니다.");
        }
        // 3. 엔티티 만들기
        TaskEntity taskEntity = new TaskEntity(user, title, content);
        // 4. 저장
        taskRepository.save(taskEntity);
        // 5. responseDto 생성
        TaskCreateResponseDto taskCreateResponseDto = new TaskCreateResponseDto(taskEntity.getId(), userName, title, content);
        // 6. 반환
        return taskCreateResponseDto;
    }

    //태스크 전체 조회
    @Transactional
    public TaskFindAllResponseDto findAllTask() {
        // 1. 데이터 준비
        // 2.검증로직 작성 필요시

        // 3. 리스트 조회
        List<TaskEntity> tasks = taskRepository.findBySoftDeleted(false);
        // 4. reponseDto 만들기
        List<TasksDto> tasksDtoList = tasks.stream()
                .map(task -> new TasksDto(task.getId(), task.getUser(), task.getTitle(), task.getContent()))
                .toList();
        TaskFindAllResponseDto taskFindAllResponseDto = new TaskFindAllResponseDto(tasksDtoList);
        // 5. responseDto 반환
        return taskFindAllResponseDto;
    }

    //태스크 단건 조회
    @Transactional
    public TaskFindResponseDto findByIdTask(Long taskId) {
        // 2. 검증로직 3. 조회
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 Task가 존재하지 않습니다."));
        // 4. Dto 만들기
        TaskFindResponseDto taskFindResponseDto = new TaskFindResponseDto(
                taskEntity.getId(), taskEntity.getUser(), taskEntity.getTitle(), taskEntity.getContent()
        );
        // 5. Dto 반환
        return taskFindResponseDto;
    }

    //태스크 수정
    @Transactional
    public TaskUpdateResponseDto updateTask(Long taskId, TaskUpdateRequestDto taskUpdateRequestDto, HttpServletRequest httpServletRequest) {
        // 1. 데이터
        String newTitle = taskUpdateRequestDto.getTitle();
        String newContent = taskUpdateRequestDto.getContent();
        String authHeader = httpServletRequest.getHeader("Authorization");
        // 2. 검증로직 3.조회
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 Task가 존재하지 않습니다."));
        if(!authHeader.startsWith("Bearer")) {
            throw new IllegalArgumentException("토큰을 인식하지 못했습니다.");
        }
        String token = authHeader.substring(7);
        Long idInToken = jwtService.parserJwtForId(token);
        Long userId = task.getUser().getId();
        if(!userId.equals(idInToken)) {
            throw new IllegalArgumentException("로그인한 계정 정보와 입력하신 userId가 일치하지 않습니다.");
        }
        // 4. 업데이트
        task.updateTitleAndContent(newTitle, newContent);
        taskRepository.save(task);
        // 5. Dto 만들기
        TaskUpdateResponseDto taskUpdateResponseDto = new TaskUpdateResponseDto(
                taskId, task.getUser(), newTitle, newContent
        );
        // 6. Dto 반환
        return taskUpdateResponseDto;
    }

    //태스크 삭제
    @Transactional
    public TaskDeleteResponseDto deleteTask(Long taskId, HttpServletRequest httpServletRequest) {
        // 1. 데이터 준비
        String authHeader = httpServletRequest.getHeader("Authorization");
        //2. 검증로직 3. 조회
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 Task가 존재하지 않습니다."));
        if(!authHeader.startsWith("Bearer")) {
            throw new IllegalArgumentException("토큰을 인식하지 못했습니다.");
        }
        String token = authHeader.substring(7);
        Long idInToken = jwtService.parserJwtForId(token);
        Long userId = task.getUser().getId();
        if(!userId.equals(idInToken)) {
            throw new IllegalArgumentException("로그인한 계정 정보와 입력하신 userId가 일치하지 않습니다.");
        }
        // 4. 삭제
        task.taskSoftDeleted();
        // 5. Dto 만들기
        TaskDeleteResponseDto taskDeleteResponseDto = new TaskDeleteResponseDto("삭제가 완료되었습니다.");
        // 6. Dto 반환
        return taskDeleteResponseDto;
    }
}
