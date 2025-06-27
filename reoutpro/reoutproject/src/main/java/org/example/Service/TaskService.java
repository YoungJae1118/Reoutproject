package org.example.Service;

import org.example.Dto.RequestDto.Task.TaskCreateRequestDto;
import org.example.Dto.RequestDto.Task.TaskUpdateRequestDto;
import org.example.Dto.ResponseDto.Task.TaskCreateResponseDto;
import org.example.Dto.ResponseDto.Task.TaskDeleteResponseDto;
import org.example.Dto.ResponseDto.Task.TaskFindResponseDto;
import org.example.Dto.ResponseDto.Task.TaskUpdateResponseDto;
import org.example.Entity.TaskEntity;
import org.example.Entity.UserEntity;
import org.example.Repository.TaskRepository;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
    }

    //태스크 생성
    public TaskCreateResponseDto createTask(TaskCreateRequestDto taskCreateRequestDto) {
        // 1. 데이터 준비
        Long id = taskCreateRequestDto.getId();
        UserEntity user = taskCreateRequestDto.getUser();
        String title = taskCreateRequestDto.getTitle();
        String content = taskCreateRequestDto.getContent();
        String userName = taskCreateRequestDto.getUser().getName();
        // 2. 검증로직

        // 3. 엔티티 만들기
        TaskEntity taskEntity = new TaskEntity(id, user, title, content);
        // 4. 저장
        taskRepository.save(taskEntity);
        // 5. responseDto 생성
        TaskCreateResponseDto taskCreateResponseDto = new TaskCreateResponseDto(id, userName, title, content);
        // 6. 반환
        return taskCreateResponseDto;
    }

    //태스크 전체 조회
    public List<TaskFindResponseDto> findAllTask() {
        // 1. 데이터 준비
        List<TaskEntity> taskEntityList;
        List<TaskFindResponseDto> taskFindResponseDtoList = new ArrayList<>();
        // 2.검증로직 작성 필요시

        // 3. 리스트 조회
        taskEntityList = taskRepository.findAll();
        // 4. reponseDto 만들기
        for (TaskEntity task : taskEntityList) {
            TaskFindResponseDto taskFindResponseDto = new TaskFindResponseDto(
                    task.getId(), task.getUser(), task.getTitle(), task.getContent()
            );
            taskFindResponseDtoList.add(taskFindResponseDto);
        }
        // 5. responseDto 반환
        return taskFindResponseDtoList;
    }


    //태스크 단건 조회
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
        // 6. Dto 반환
        return taskUpdateResponseDto;
    }

    //태스크 삭제
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

