package ru.kovalchuk.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kovalchuk.task.dao.TaskDao;
import ru.kovalchuk.task.model.AddTaskRequest;
import ru.kovalchuk.task.model.EditTaskRequest;
import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.model.TaskFilter;
import ru.kovalchuk.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("task")
public class TaskController {

    private final TaskDao taskDao;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "dataSearch", required = false) String dataSearch,
                                                  @RequestParam(value = "processingTask", required = false) boolean processingTask,
                                                  @AuthenticationPrincipal User user) {
        List<Task> result;
        TaskFilter filter = new TaskFilter();
        filter.setUserId(user.getId());
        filter.setOnlyProcessing(processingTask);
        filter.setSearchString(dataSearch);
        result = taskDao.getTasks(filter);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTask(@PathVariable("task_id") Long taskId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskDao.getById(taskId, user));
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@Valid @RequestBody AddTaskRequest request,
                                        @AuthenticationPrincipal User user) {
        Long id = taskDao.addTask(request.getNameTask(), user);
        URI location = URI.create("/task/" + id);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTask(@PathVariable("task_id") Long taskId,
                         @Valid @RequestBody EditTaskRequest request,
                         @AuthenticationPrincipal User user) {
        if (request.getNewTaskName() != null){
            taskDao.editTask(taskId, request.getNewTaskName(), user);
        }
        if (request.isChangeStatus()){
            taskDao.toggleTask(taskId, user);
        }
    }

    @DeleteMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("task_id") @Min(0) Long taskId,
                           @AuthenticationPrincipal User user){
        taskDao.deleteTask(taskId, user);
    }
}