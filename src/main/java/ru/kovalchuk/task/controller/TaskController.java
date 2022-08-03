package ru.kovalchuk.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kovalchuk.task.dao.TaskDao;
import ru.kovalchuk.task.model.AddTaskRequest;
import ru.kovalchuk.task.model.EditTaskRequest;
import ru.kovalchuk.task.model.Task;

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
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "dataSearch", required = false) String dataSearch) {
        List<Task> result;
        if (dataSearch == null){
            result = taskDao.getAllTasks();
        }
        else{
            result = taskDao.findByNameSubstring(dataSearch);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTask(@PathVariable("task_id") Long taskId) {
        return ResponseEntity.ok(taskDao.getById(taskId));
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@Valid @RequestBody AddTaskRequest request) {
        Long id = taskDao.addTask(request.getNameTask());
        URI location = URI.create("/task/" + id);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTask(@PathVariable("task_id") Long taskId,
                         @Valid @RequestBody EditTaskRequest request) {
        if (request.getNewTaskName() != null){
            taskDao.editTask(taskId, request.getNewTaskName());
        }
        if (request.isChangeStatus()){
            taskDao.toggleTask(taskId);
        }
    }

    @DeleteMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("task_id") @Min(0) Long taskId){
        taskDao.deleteTask(taskId);
    }
}