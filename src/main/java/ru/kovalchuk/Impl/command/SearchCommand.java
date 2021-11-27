package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Printer;
import ru.kovalchuk.TaskDao;

import java.util.List;

@Slf4j
public class SearchCommand extends BaseCommand{

    public SearchCommand(TaskDao taskDao, Printer printer) {
        super(taskDao, printer);
    }

    @Override
    public void execute(List<String> params) {
        String searchString = params.get(0);
        if (!taskDao.findByNameSubstring(searchString).isEmpty()){
            printer.printTasks(taskDao.findByNameSubstring(searchString));
        } else {
            log.error("Ничего не найдено");
        }
    }
}
