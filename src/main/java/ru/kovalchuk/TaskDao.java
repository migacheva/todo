package ru.kovalchuk;
import java.util.List;

public interface TaskDao {

    Task getById(int id);

    List<Task> getAllTasks();

    List<Task> getProcessingTasks();

    List<Task> findByNameSubstring(String value);

    void addTask(String taskName);

    void deleteTask(int id);

    void editTask(int id, String name);

    void toggleTask(int id);

}
