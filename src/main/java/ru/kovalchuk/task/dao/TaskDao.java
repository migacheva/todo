package ru.kovalchuk.task.dao;

import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.user.model.User;

import java.util.List;

public interface TaskDao {

    Task getById(Long id, User user);

    List<Task> getAllTasks(User user);

    List<Task> getProcessingTasks(User user);

    List<Task> findByNameSubstring(String value, User user);

    Long addTask(String taskName, User user);

    void deleteTask(Long id, User user);

    void editTask(Long id, String name, User user);

    void toggleTask(Long id, User user);

}
