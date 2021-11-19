package ru.kovalchuk.Impl.command;

import ru.kovalchuk.Printer;
import ru.kovalchuk.TaskDao;

public class PrintCommand extends BaseCommand {

    public PrintCommand(TaskDao taskDao, Printer printer) {
        super.taskDao = taskDao;

    }

    @Override
    public void execute() {
//        taskDao.printTasks(taskDao.getAllTasks());
    }
}
