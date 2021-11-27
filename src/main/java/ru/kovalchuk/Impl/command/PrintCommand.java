package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Printer;
import ru.kovalchuk.Task;
import ru.kovalchuk.TaskDao;

import java.util.List;
import java.util.Optional;

@Slf4j
public class PrintCommand extends BaseCommand {

    public PrintCommand(TaskDao taskDao, Printer printer) {
        super(taskDao, printer);
    }

    @Override
    public void execute(List<String> params) {
        Optional<String> argAll = params.stream()
                                        .findFirst();
        boolean printAll = argAll.map(s -> s.equals("all"))
                                 .orElse(false);
        List<Task> taskList = taskDao.getTasks(printAll);
        if (!taskList.isEmpty()){
            printer.printTasks(taskList);
        } else {
            log.error("Задач для вывода нет");
        }
    }
}
