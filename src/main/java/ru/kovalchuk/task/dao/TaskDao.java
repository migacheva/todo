package ru.kovalchuk.task.dao;

import ru.kovalchuk.task.model.Task;

import java.util.List;

public interface TaskDao {

    Task getById(Long id);

    List<Task> getAllTasks();

    List<Task> getProcessingTasks();

    List<Task> findByNameSubstring(String value);

    Long addTask(String taskName);

    void deleteTask(Long id);

    void editTask(Long id, String name);

    void toggleTask(Long id);

}
