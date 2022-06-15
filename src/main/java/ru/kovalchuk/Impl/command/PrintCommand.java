package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kovalchuk.Impl.ConsolePrinter;

@Slf4j
@Component
public class PrintCommand extends BaseCommand {
    @Autowired
    private ConsolePrinter consolePrinter;

    public PrintCommand() {
        commandName = "print";
    }

    @Override
    public void execute(Object [] params) {
        boolean printAll = (boolean) params[0];
        if (printAll){
            if (!taskDao.getAllTasks().isEmpty()){
                consolePrinter.printTasks(taskDao.getAllTasks());
            } else {
                log.error("Задач для вывода нет");
            }
        } else {
            if (!taskDao.getProcessingTasks().isEmpty()) {
                consolePrinter.printTasks(taskDao.getProcessingTasks());
            } else {
                log.error("Задач для вывода нет");
            }
        }
    }
}
