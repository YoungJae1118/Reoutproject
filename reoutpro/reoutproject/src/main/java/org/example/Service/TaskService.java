package org.example.Service;

import org.example.Entity.TaskEntity;
import org.example.Entity.UserEntity;
import org.example.Repository.TaskRepository;
import org.example.Repository.UserRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
//태스크 생성
    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }
    //태스크 전체 조회
    public List<TaskEntity> findAllTask() {
        return taskRepository.findAll();
    }
    //태스크 단건 조회 (타이틀, 이름)
    public List<TaskEntity> findByTitleTask(String title) {
        return taskRepository.findByTitle(title);
    }

    public List<TaskEntity> findByNameTask(UserEntity user) {
        return taskRepository.findByUser(user);
    }
    //태스크 삭제
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
