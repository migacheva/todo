package ru.kovalchuk.Impl.command;

import ru.kovalchuk.TaskDao;

public class GetById extends BaseCommand{

    private int id;

    public GetById(TaskDao taskDao, int id){
        super.taskDao = taskDao;

        this.id = id;
    }

    @Override
    public void execute() {
        taskDao.getById(id);
    }
}
