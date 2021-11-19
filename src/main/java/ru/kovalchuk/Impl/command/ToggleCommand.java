package ru.kovalchuk.Impl.command;

import ru.kovalchuk.TaskDao;

public class ToggleCommand extends BaseCommand{

    private int id;

    public ToggleCommand(TaskDao taskDao, int id){
        super.taskDao = taskDao;

        this.id = id;
    }

    @Override
    public void execute() {
        taskDao.toggleTask(id);
    }
}
