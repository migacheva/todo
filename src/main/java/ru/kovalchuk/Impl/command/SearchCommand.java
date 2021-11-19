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
        // boolean somethingSearched = false;
        // for (int i = 0; i < taskList.size(); i++) {
        //     if (taskList.get(i).getName().contains(searchData)) {
        //         printTaskInfo(i, taskList.get(i));
        //         somethingSearched = true;
        //     }
        // }
        // if (!somethingSearched) {
        //     log.error("Ничего не найдено");
        // }
        taskDao.findByNameSubstring(searchString);
    }
}
