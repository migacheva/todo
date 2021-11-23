package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

@Slf4j
public class DeleteCommand extends BaseCommand {

    private final int id;

    public DeleteCommand(TaskDao taskDao, int id) {
        super.taskDao = taskDao;

        this.id = id - 1;
    }

    @Override
    public void execute() {
        try {
            taskDao.deleteTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        }
    }
}
