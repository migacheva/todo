package ru.kovalchuk.Impl.command;

import ru.kovalchuk.Command;
import ru.kovalchuk.TaskDao;

public class EditCommand extends BaseCommand {

    private int id;
    private String newName;

    public EditCommand(TaskDao taskDao, int id, String newName) {
        super.taskDao = taskDao;

        this.id = id;
        this.newName = newName;
    }

    @Override
    public void execute() {
        taskDao.editTask(id, newName);
    }
}
