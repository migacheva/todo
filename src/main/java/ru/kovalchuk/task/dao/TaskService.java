package ru.kovalchuk.task.dao;

import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.model.TaskFilter;
import ru.kovalchuk.user.model.User;

import java.util.List;

public interface TaskService {

    Task getById(Long id, User user);

    List<Task> getTasks(User user, String dataSearch, boolean processingTask);

    Long addTask(String taskName, User user);

    void deleteTask(Long id, User user);

    void editTask(Long id, String name, User user);

    void toggleTask(Long id, User user);

}
