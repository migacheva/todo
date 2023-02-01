package ru.kovalchuk.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kovalchuk.task.dao.TaskMapper;
import ru.kovalchuk.task.dao.TaskService;
import ru.kovalchuk.task.model.*;
import ru.kovalchuk.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(value = "dataSearch", required = false) String dataSearch,
                                                  @RequestParam(value = "processingTask", required = false) boolean processingTask,
                                                  @AuthenticationPrincipal User user) {
        List<Task> res = taskService.getTasks(user, dataSearch, processingTask);
        // List<TaskDTO> result = res.stream()
        //         .map((task) -> taskMapper.toDTO(task))
        //         .collect(Collectors.toList());
        List<TaskDTO> result = res.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("task_id") Long taskId, @AuthenticationPrincipal User user) {
        Task task = taskService.getById(taskId, user);
        TaskDTO taskDto = taskMapper.toDTO(task);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@Valid @RequestBody AddTaskRequest request,
                                        @AuthenticationPrincipal User user) {
        Long id = taskService.addTask(request.getNameTask(), user);
        URI location = URI.create("/task/" + id);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTask(@PathVariable("task_id") Long taskId,
                         @Valid @RequestBody EditTaskRequest request,
                         @AuthenticationPrincipal User user) {
        if (request.getNewTaskName() != null){
            taskService.editTask(taskId, request.getNewTaskName(), user);
        }
        if (request.isChangeStatus()){
            taskService.toggleTask(taskId, user);
        }
    }

    @DeleteMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("task_id") @Min(0) Long taskId,
                           @AuthenticationPrincipal User user){
        taskService.deleteTask(taskId, user);
    }
}