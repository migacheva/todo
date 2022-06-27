package ru.kovalchuk.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.kovalchuk.task.model.AddTaskRequest;
import ru.kovalchuk.task.model.EditTaskRequest;
import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.dao.TaskDao;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskDao taskDao;

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
    public ResponseEntity<Task> getTask(@PathVariable("task_id") @NonNull int taskId) {
        return ResponseEntity.ok(taskDao.getById(taskId));
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody AddTaskRequest request) {
        int id = taskDao.addTask(request.getNameTask());
        URI location = URI.create("/task/" + id);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTask(@PathVariable("task_id") @NonNull int taskId,
                         @RequestBody EditTaskRequest request) {
        if (request.getNewTaskName() != null){
            taskDao.editTask(taskId, request.getNewTaskName());
        }
        if (request.isChangeStatus()){
            taskDao.toggleTask(taskId);
        }
    }

    @DeleteMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("task_id") @NonNull int taskId){
        taskDao.deleteTask(taskId);
    }
}