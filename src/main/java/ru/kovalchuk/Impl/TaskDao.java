package ru.kovalchuk.Impl;
import ru.kovalchuk.Task;
import java.util.ArrayList;
import java.util.List;


public class TaskDao{

    public static TaskDao instance;
    public static TaskDao getInstance(){
        if (instance == null){
            instance = new TaskDao();
        }
        return instance;
    }
    private TaskDao(){
        taskList = new ArrayList<>();
    }

    private List<Task> taskList;


    public Task getById(int id) {
        return taskList.get(id);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public List<Task> getProcessingTasks() {
        List<Task> result = new ArrayList<>();
        for (int i=0; i<taskList.size(); i++){
            if (!taskList.get(i).isDone()){
                result.add(getById(i));
            }
        }
        return result;
    }

    public List<Task> findByNameSubstring(String value) {
        List<Task> result = new ArrayList<>();
         for (int i = 0; i < taskList.size(); i++) {
             if (taskList.get(i).getName().contains(value)) {
                 result.add(getById(i));
             }
         }
        return result;
    }

    public void addTask(String taskName) {
        int id = taskList.stream()
                .mapToInt(Task::getId)
                .max().orElse(0);
        taskList.add(new Task(id + 1, taskName));
    }

    public void deleteTask(int id) {
        taskList.remove(id);
        // Синхронизация id в списке с id в Task
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setId(i + 1);
        }
    }


    public void editTask(int id, String name) {
        taskList.get(id).setName(name);
    }


    public void toggleTask(int id) {
        taskList.get(id).toggle();
    }
}
