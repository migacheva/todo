package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.TaskDao;

@Slf4j
public class AddCommand extends BaseCommand {

    private String nameTask;
    public AddCommand(String nameTask){
        this.nameTask = nameTask;
    }

    public void execute(){
        if (nameTask.isBlank()) {
            log.error("Вводить пустые строки, пробелы, перенос строки.");
        } else {
            TaskDao.getInstance().addTask(nameTask);
        }
    }
}
