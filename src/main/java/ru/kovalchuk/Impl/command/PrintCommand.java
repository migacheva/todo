package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Printer;
import ru.kovalchuk.TaskDao;

@Slf4j
public class PrintCommand extends BaseCommand {

    private final Boolean printAll;

    public PrintCommand(TaskDao taskDao, Printer printer, Boolean printAll) {
        super.taskDao = taskDao;
        super.printer = printer;

        this.printAll = printAll;
    }

    @Override
    public void execute() {
        if (printAll){
            if (!taskDao.getAllTasks().isEmpty()){
                printer.printTasks(taskDao.getAllTasks());
            } else {
                log.error("Задач для вывода нет");
            }
        } else {
            if (!taskDao.getProcessingTasks().isEmpty()) {
                printer.printTasks(taskDao.getProcessingTasks());
            } else {
                log.error("Задач для вывода нет");
            }
        }
    }
}
