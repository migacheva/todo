package ru.kovalchuk.task.dao;

import ru.kovalchuk.task.model.TaskFilter;
import ru.kovalchuk.task.model.Task;

import java.util.List;

public interface CustomTaskRepository {

    List<Task> getTasks(TaskFilter filter);
}
