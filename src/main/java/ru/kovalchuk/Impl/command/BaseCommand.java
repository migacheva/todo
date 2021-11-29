package ru.kovalchuk.Impl.command;

import ru.kovalchuk.Command;
import ru.kovalchuk.Printer;
import ru.kovalchuk.TaskDao;

public abstract class BaseCommand implements Command {

    protected TaskDao taskDao;
    protected Printer printer;

    public BaseCommand(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    public BaseCommand(TaskDao taskDao, Printer printer){
        this(taskDao);
        this.printer = printer;
    }
}
