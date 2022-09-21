package ru.kovalchuk.task.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.model.TaskFilter;
import ru.kovalchuk.user.model.User;

import java.util.List;

@Component
@Primary
public class TaskServiceImpl implements TaskDao{

    public final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @Override
    public Task getById(Long id, User user) {
        return taskRepository.getReferenceById(id);
    }

    @Override
    public List<Task> getTasks(TaskFilter filter) {
        return taskRepository.getTasks(filter);
    }

    @Override
    public Long addTask(String taskName, User user) {
        Task newTask = new Task();
        newTask.setName(taskName);
        newTask.setUser(user);
        newTask = taskRepository.save(newTask);
        return newTask.getId();
    }

    @Override
    public void deleteTask(Long id, User user) {
        taskRepository.deleteById(id);
    }

    @Override
    public void editTask(Long id, String name, User user) {
        Task editTask = getById(id, user);
        editTask.setName(name);
        taskRepository.save(editTask);
    }

    @Override
    public void toggleTask(Long id, User user) {
        Task toggleTask = getById(id, user);
        toggleTask.toggle();
        taskRepository.save(toggleTask);
    }
}
