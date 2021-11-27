package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

import java.util.List;

@Slf4j
public class EditCommand extends BaseCommand {

    public EditCommand(TaskDao taskDao) {
        super(taskDao);
    }

    @Override
    public void execute(List<String> params) {
        int id = Integer.parseInt(params.get(0)) - 1;
        String newName = params.get(1);
        try {
            taskDao.editTask(id, newName);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        } catch (NumberFormatException e){
            log.error("Необходимо ввести id задачи");
        }
    }
}
