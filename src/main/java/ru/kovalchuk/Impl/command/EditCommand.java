package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EditCommand extends BaseCommand {
    public EditCommand() {
        commandName = "edit";
    }

    @Override
    public void execute(Object [] params) {
        int id = Integer.parseInt((String) params[0]) - 1;
        String newName = (String) params[1];
        try {
            taskDao.editTask(id, newName);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        } catch (NumberFormatException e){
            log.error("Необходимо ввести id задачи");
        }
    }
}
