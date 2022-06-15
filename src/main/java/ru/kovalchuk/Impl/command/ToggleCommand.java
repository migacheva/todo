package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ToggleCommand extends BaseCommand{

    public ToggleCommand(){
        commandName = "toggle";
    }

    @Override
    public void execute(Object [] params) {
        int id = Integer.parseInt((String) params[0]) - 1;
        try {
            taskDao.toggleTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет", e);
        }
    }
}
