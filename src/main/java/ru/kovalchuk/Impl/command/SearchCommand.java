package ru.kovalchuk.Impl.command;

import ru.kovalchuk.TaskDao;

public class SearchCommand extends BaseCommand{
    // как добавить поиск по id

    private String searchString;

    public SearchCommand(TaskDao taskDao, String searchString) {
        super.taskDao = taskDao;

        this.searchString = searchString;
    }

    @Override
    public void execute() {
        taskDao.findByNameSubstring(searchString);
    }
}
