package org.example.Contorller;

import org.example.Dto.RequestDto.Task.TaskCreateRequestDto;
import org.example.Dto.RequestDto.Task.TaskUpdateRequestDto;
import org.example.Dto.ResponseDto.Task.TaskCreateResponseDto;
import org.example.Dto.ResponseDto.Task.TaskDeleteResponseDto;
import org.example.Dto.ResponseDto.Task.TaskFindResponseDto;
import org.example.Dto.ResponseDto.Task.TaskUpdateResponseDto;
import org.example.Repository.UserRepository;
import org.example.Service.TaskService;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService, UserRepository userRepository, UserService userService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskCreateResponseDto createTaskControl(@RequestBody TaskCreateRequestDto taskCreateRequestDto) {
        return taskService.createTask(taskCreateRequestDto);
    }

    @GetMapping
    public List<TaskFindResponseDto> findAllTaskControl() {
        return taskService.findAllTask();
    }

    @GetMapping("/{taskId}")
    public TaskFindResponseDto findByIdtaskControl(@PathVariable Long taskId) {
        return taskService.findByIdTask(taskId);
    }

    @PutMapping("{/taskId}")
    public TaskUpdateResponseDto updateTaskControl(@PathVariable Long taskId, @RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        return taskService.updateTask(taskId, taskUpdateRequestDto);
    }

    @DeleteMapping("/{taskId}")
    public TaskDeleteResponseDto deleteTaskControl(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
    /*
        @GetMapping("/find")
        public List<TaskEntity> findByTitleTask(@RequestParam String title) {
            return taskService.findByTitleTask(title);
        }

        @GetMapping("/find")
        public List<TaskEntity> findByUserTask(@RequestParam String name) {
            List<UserEntity> allUser = userRepository.findByName(name);
            List<TaskEntity> allTask = new ArrayList<>();
            //미숙 반복하기
            for (UserEntity user : allUser){
                allTask.addAll(taskService.findByNameTask(user));
            }
            return allTask;
        }
    */

