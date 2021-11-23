package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

@Slf4j
public class AddCommand extends BaseCommand {

    private String nameTask;

    public AddCommand(TaskDao taskDao, String nameTask){
        super.taskDao = taskDao;

        this.nameTask = nameTask;
    }

    public void execute(){
        if (nameTask.isBlank()) {
            log.error("Вводить пустые строки, пробелы, перенос строки.");
        } else {
            taskDao.addTask(nameTask);
        }
    }
}
