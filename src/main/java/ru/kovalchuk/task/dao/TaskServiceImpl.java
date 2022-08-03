package ru.kovalchuk.task.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.kovalchuk.task.model.Task;

import java.util.List;

@Component
@Primary
public class TaskServiceImpl implements TaskDao{

    public final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @Override
    public Task getById(Long id) {
        return taskRepository.getReferenceById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getProcessingTasks() {
        return null;
    }

    @Override
    public List<Task> findByNameSubstring(String value) {
        return taskRepository.findByNameContains(value);
    }

    @Override
    public Long addTask(String taskName) {
        Task newTask = new Task();
        newTask.setName(taskName);
        newTask = taskRepository.save(newTask);
        return newTask.getId();
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void editTask(Long id, String name) {
        Task editTask = getById(id);
        editTask.setName(name);
        taskRepository.save(editTask);
    }

    @Override
    public void toggleTask(Long id) {
        Task toggleTask = getById(id);
        toggleTask.toggle();
        taskRepository.save(toggleTask);
    }
}
