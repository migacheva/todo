package ru.kovalchuk.task.dao;

import org.springframework.stereotype.Component;
import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.user.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDaoImpl implements TaskDao {

    public TaskDaoImpl(){
        taskList = new ArrayList<>();
    }

    private final List<Task> taskList;


    public Task getById(Long id, User user) {
        return taskList.get(id.intValue());
    }

    public List<Task> getAllTasks(User user) {
        return taskList;
    }

    public List<Task> getProcessingTasks(User user) {
        List<Task> result = new ArrayList<>();
        for (int i=0; i<taskList.size(); i++){
            if (!taskList.get(i).isDone()){
                result.add(getById((long) i, user));
            }
        }
        return result;
    }

    public List<Task> findByNameSubstring(String value, User user) {
        List<Task> result = new ArrayList<>();
         for (int i = 0; i < taskList.size(); i++) {
             if (taskList.get(i).getName().contains(value)) {
                 result.add(getById((long) i, user));
             }
         }
        return result;
    }

    public Long addTask(String taskName, User user) {
        long id = taskList.stream().mapToLong(Task::getId)
                  .max().orElse(0);
        taskList.add(new Task((id + 1), taskName));
        return id;
    }

    public void deleteTask(Long id, User user) {
        taskList.remove(id);
        // Синхронизация id в списке с id в Task
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setId((long) (i + 1));
        }
    }


    public void editTask(Long id, String name, User user) {
        taskList.get(id.intValue()).setName(name);
    }


    public void toggleTask(Long id, User user) {
        taskList.get(id.intValue()).toggle();
    }
}
