package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Dto.RequestDto.Task.TaskCreateRequestDto;
import org.example.Dto.RequestDto.Task.TaskUpdateRequestDto;
import org.example.Dto.ResponseDto.Task.*;
import org.example.Entity.TaskEntity;
import org.example.Entity.UserEntity;
import org.example.Repository.TaskRepository;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    //태스크 생성
    @Transactional
    public TaskCreateResponseDto createTask(Long id, TaskCreateRequestDto taskCreateRequestDto) {
        // 1. 데이터 준비
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 이용자가 없습니다."));
        String title = taskCreateRequestDto.getTitle();
        String content = taskCreateRequestDto.getContent();
        String userName = user.getName();
        // 2. 검증로직

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
        List<TaskEntity> tasks;
        List<TasksDto> tasksDtoList;
        // 2.검증로직 작성 필요시

        // 3. 리스트 조회
        tasks = taskRepository.findAll();
        // 4. reponseDto 만들기
        tasksDtoList = tasks.stream()
                .map(task -> new TasksDto(task.getId(), task.getUser(), task.getTitle(), task.getContent()))
                .toList();
        TaskFindAllResponseDto taskFindAllResponseDto = new TaskFindAllResponseDto(tasksDtoList);
        // 5. responseDto 반환
        return taskFindAllResponseDto;
    }

    //태스크 단건 조회
    @Transactional
    public TaskFindResponseDto findByIdTask(Long id) {
        // 1. 데이터
        TaskEntity taskEntity = new TaskEntity();
        // 2. 검증로직 3. 조회
        taskEntity = taskRepository.findById(id)
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
    public TaskUpdateResponseDto updateTask(Long id, TaskUpdateRequestDto taskUpdateRequestDto) {
        // 1. 데이터
        Long newId = id;
        String newTitle = taskUpdateRequestDto.getTitle();
        String newContent = taskUpdateRequestDto.getContent();
        // 2. 검증로직 3.조회
        TaskEntity oldTask = taskRepository.findById(newId)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 Task가 존재하지 않습니다."));
        // 4. 업데이트
        TaskEntity newTask = new TaskEntity(
                newId, oldTask.getUser(), newTitle, newContent
        );
        // 5. Dto 만들기
        TaskUpdateResponseDto taskUpdateResponseDto = new TaskUpdateResponseDto(
                newId, oldTask.getUser(), newTitle, newContent
        );
        taskRepository.save(newTask);
        // 6. Dto 반환
        return taskUpdateResponseDto;
    }

    //태스크 삭제
    @Transactional
    public TaskDeleteResponseDto deleteTask(Long id) {
        // 1. 데이터 준비

        //2. 검증로직 3. 조회
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 Task가 존재하지 않습니다."));
        // 4. 삭제
        taskRepository.deleteById(id);
        // 5. Dto 만들기
        TaskDeleteResponseDto taskDeleteResponseDto = new TaskDeleteResponseDto("삭제가 완료되었습니다.");
        // 6. Dto 반환
        return taskDeleteResponseDto;
    }
}
    /*
        //태스크 필터 조회 (타이틀, 이름)
        public List<TaskEntity> findByTitleTask(String title) {
            return taskRepository.findByTitle(title);
        }

        public List<TaskEntity> findByNameTask(UserEntity user) {
            return taskRepository.findByUser(user);
        }

     */

