package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddCommand extends BaseCommand {

    public AddCommand(){
        commandName = "add";
    }

    public void execute(Object[] params){
        String nameTask = (String) params[0];
        if (nameTask == null || nameTask.isBlank()) {
            log.error("Вводить пустые строки, пробелы, перенос строки.");
        } else {
            taskDao.addTask(nameTask);
        }
    }
}
