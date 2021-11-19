package ru.kovalchuk.Impl;

import ru.kovalchuk.Task;
import ru.kovalchuk.TaskDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TaskDaoImpl implements TaskDao {

    List<Task> taskList = new ArrayList<>();

    @Override
    public Task getById(int id) {
        return taskList.get(id);
    }

    @Override
    public List<Task> findByNameSubstring(String value) {
        return null;
    }

    @Override
    public void addTask(String taskName) {
        int id = taskList.stream()
                .mapToInt(Task::getId)
                .max().orElse(0);
        taskList.add(new Task(id + 1, taskName));
    }

    @Override
    public void deleteTask(int id) {
        taskList.remove(id);
        // Синхронизация id в списке с id в Task
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setId(i);
        }
    }

    @Override
    public void editTask(int id, String name) {
        taskList.get(id).setName(name);
    }

    @Override
    public void toggleTask(int id) {
        taskList.get(id).toggle();
    }
}
