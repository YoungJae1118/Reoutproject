package org.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{userId}")
    public TaskCreateResponseDto createTaskControl(@PathVariable Long userId, @RequestBody TaskCreateRequestDto taskCreateRequestDto) {
        return taskService.createTask(userId, taskCreateRequestDto);
    }

    @GetMapping
    public TaskFindAllResponseDto findAllTaskControl() {
        return taskService.findAllTask();
    }

    @GetMapping("/{taskId}")
    public TaskFindResponseDto findByIdTaskControl(@PathVariable Long taskId) {
        return taskService.findByIdTask(taskId);
    }

    @PutMapping("/{taskId}")
    public TaskUpdateResponseDto updateTaskControl(@PathVariable Long taskId, @RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        return taskService.updateTask(taskId, taskUpdateRequestDto);
    }

    @DeleteMapping("/{taskId}")
    public TaskDeleteResponseDto deleteTaskControl(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }
}