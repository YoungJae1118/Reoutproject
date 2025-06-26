package org.example.Contorller;

import org.example.Entity.TaskEntity;
import org.example.Entity.UserEntity;
import org.example.Repository.UserRepository;
import org.example.Service.TaskService;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.config.TaskExecutionOutcome;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserRepository userRepository;

    public TaskController(TaskService taskService,UserRepository userRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public TaskEntity taskCrete(TaskEntity task) {
       return taskService.createTask(task);
    }

    @GetMapping("/find")
    public List<TaskEntity> findAllTask() {
        return taskService.findAllTask();
    }

    @GetMapping("/find")
    public List<TaskEntity> findByTitleTask(@RequestParam String title) {
        return taskService.findByTitleTask(title);
    }

    @GetMapping("/find")
    public List<TaskEntity> findByUserTask(@RequestParam String name) {
        //이름으로 태스크 조회하는 기능
    }
    @DeleteMapping("/delete")
    public void deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
        //
    }
}
