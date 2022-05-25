package ru.kovalchuk.Impl.command;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.ConsolePrinter;
import ru.kovalchuk.Impl.TaskDao;

@Slf4j
public class SearchCommand extends BaseCommand{
    private final String searchString;

    public SearchCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute() {
        if (!TaskDao.getInstance().findByNameSubstring(searchString).isEmpty()){
            ConsolePrinter.getInstance().printTasks(TaskDao.getInstance().findByNameSubstring(searchString));
        } else {
            log.error("Ничего не найдено");
        }
    }
}
