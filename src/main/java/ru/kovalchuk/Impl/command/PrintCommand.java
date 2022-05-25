package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.ConsolePrinter;
import ru.kovalchuk.Impl.TaskDao;

@Slf4j
public class PrintCommand extends BaseCommand {

    private final Boolean printAll;

    public PrintCommand(Boolean printAll) {
        this.printAll = printAll;
    }

    @Override
    public void execute() {
        if (printAll){
            if (!TaskDao.getInstance().getAllTasks().isEmpty()){
                ConsolePrinter.getInstance().printTasks(TaskDao.getInstance().getAllTasks());
            } else {
                log.error("Задач для вывода нет");
            }
        } else {
            if (!TaskDao.getInstance().getProcessingTasks().isEmpty()) {
                ConsolePrinter.getInstance().printTasks(TaskDao.getInstance().getProcessingTasks());
            } else {
                log.error("Задач для вывода нет");
            }
        }
    }
}
