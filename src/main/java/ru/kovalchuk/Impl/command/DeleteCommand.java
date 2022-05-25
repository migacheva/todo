package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.TaskDao;

@Slf4j
public class DeleteCommand extends BaseCommand {

    private final int id;

    public DeleteCommand(int id) {
        this.id = id - 1;
    }

    @Override
    public void execute() {
        try {
            TaskDao.getInstance().deleteTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        }
    }
}
