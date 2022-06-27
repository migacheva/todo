package ru.kovalchuk.task.dao;

import ru.kovalchuk.task.model.Task;

import java.util.List;

public interface TaskDao {

    Task getById(int id);

    List<Task> getAllTasks();

    List<Task> getProcessingTasks();

    List<Task> findByNameSubstring(String value);

    int addTask(String taskName);

    void deleteTask(int id);

    void editTask(int id, String name);

    void toggleTask(int id);

}
