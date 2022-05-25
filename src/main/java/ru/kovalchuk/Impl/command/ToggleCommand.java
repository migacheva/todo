package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.TaskDao;

@Slf4j
public class ToggleCommand extends BaseCommand{

    private int id;

    public ToggleCommand(int id){
        this.id = id - 1;
    }

    @Override
    public void execute() {
        try {
            TaskDao.getInstance().toggleTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет", e);
        }
    }
}
