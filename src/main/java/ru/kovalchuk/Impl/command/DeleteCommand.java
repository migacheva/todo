package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteCommand extends BaseCommand {

    public DeleteCommand() {
        commandName = "delete";
    }

    @Override
    public void execute(Object [] params) {
        int id = Integer.parseInt((String) params[0]) - 1;
        try {
            taskDao.deleteTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        }
    }
}
