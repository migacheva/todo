package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

import java.util.List;

@Slf4j
public class AddCommand extends BaseCommand {

    public AddCommand(TaskDao taskDao) {
        super(taskDao);
    }

    public void execute(List<String> params){
        String taskName = params.get(0);
        if (taskName.isBlank()) {
            log.error("Вводить пустые строки, пробелы, перенос строки.");
        } else {
            taskDao.addTask(taskName);
        }
    }
}
