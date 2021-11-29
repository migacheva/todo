package ru.kovalchuk.Impl;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Printer;
import ru.kovalchuk.Task;

import java.util.List;

@Slf4j
public class ConsolePrinter implements Printer {
    private static final String inProgress = ". [ ] "; // задача не выполнена
    private static final String done = ". [v] "; // задача выполнена

    @Override
    public void printTasks(List<Task> listTask) {
        listTask.stream()
                .map(this::representTask)
                .forEach(taskString -> {
                    log.info(taskString);
                    System.out.println(taskString);
                });
    }

    private String representTask(Task task) {
        return task.getId() + (task.isDone() ? done : inProgress) + task.getName();
    }
}
