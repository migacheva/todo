package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Printer;
import ru.kovalchuk.TaskDao;

@Slf4j
public class SearchCommand extends BaseCommand{
    private final String searchString;

    public SearchCommand(TaskDao taskDao, Printer printer, String searchString) {
        super.taskDao = taskDao;
        super.printer = printer;

        this.searchString = searchString;
    }

    @Override
    public void execute() {
        if (!taskDao.findByNameSubstring(searchString).isEmpty()){
            printer.printTasks(taskDao.findByNameSubstring(searchString));
        } else {
            log.error("Ничего не найдено");
        }
    }
}
