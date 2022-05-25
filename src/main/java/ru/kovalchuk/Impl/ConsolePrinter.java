package ru.kovalchuk.Impl;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Task;

import java.util.List;

@Slf4j
public class ConsolePrinter{

    private static ConsolePrinter instance;

    public static ConsolePrinter getInstance(){
        if (instance == null){
            instance = new ConsolePrinter();
        }
        return instance;
    }

    private ConsolePrinter(){
        // это приватный конструктор
    }

    private static final String inProgress = ". [ ] "; // задача не выполнена
    private static final String done = ". [v] "; // задача выполнена

    public void printTasks(List<Task> listTask) {
        listTask.forEach(task -> log.info(representTask(task)));
        listTask.forEach(task -> System.out.println(representTask(task)));
    }

    private String representTask(Task task) {
        return task.getId() + (task.isDone() ? done : inProgress) + task.getName();
    }
}
