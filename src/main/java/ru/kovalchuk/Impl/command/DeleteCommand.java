package ru.kovalchuk.Impl.command;

import ru.kovalchuk.TaskDao;

public class DeleteCommand extends BaseCommand {

    private int id;

    public DeleteCommand(TaskDao taskDao, int id) {
        super.taskDao = taskDao;

        this.id = id;
    }

    @Override
    public void execute() {
        taskDao.deleteTask(id);
    }
}
