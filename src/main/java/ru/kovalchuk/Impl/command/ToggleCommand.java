package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

@Slf4j
public class ToggleCommand extends BaseCommand{

    private int id;

    public ToggleCommand(TaskDao taskDao, int id){
        super.taskDao = taskDao;

        this.id = id - 1;
    }

    @Override
    public void execute() {
        try {
            taskDao.toggleTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет", e);
        }
    }
}
