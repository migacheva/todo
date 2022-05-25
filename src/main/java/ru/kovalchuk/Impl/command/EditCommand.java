package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.TaskDao;

@Slf4j
public class EditCommand extends BaseCommand {

    private int id;
    private String newName;

    public EditCommand(int id, String newName) {
        this.id = id - 1;
        this.newName = newName;
    }

    @Override
    public void execute() {
        try {
            TaskDao.getInstance().editTask(id, newName);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        } catch (NumberFormatException e){
            log.error("Необходимо ввести id задачи");
        }
    }
}
