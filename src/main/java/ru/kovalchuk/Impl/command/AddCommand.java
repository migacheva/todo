package ru.kovalchuk.Impl.command;

import ru.kovalchuk.TaskDao;

public class AddCommand extends BaseCommand{

    private String nameTask;

    public AddCommand(TaskDao taskDao, String nameTask){
        super.taskDao = taskDao;

        this.nameTask = nameTask;
    }

    public void execute(){
        taskDao.addTask(nameTask);
    }
}
