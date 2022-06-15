package ru.kovalchuk.Impl.command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kovalchuk.Impl.ConsolePrinter;

@Slf4j
@Component
public class SearchCommand extends BaseCommand{

    @Autowired
    private ConsolePrinter consolePrinter;

    public SearchCommand() {
        commandName = "search";
    }

    @Override
    public void execute(Object [] params) {
        String searchString = (String) params[0];
        if (!taskDao.findByNameSubstring(searchString).isEmpty()){
            consolePrinter.printTasks(taskDao.findByNameSubstring(searchString));
        } else {
            log.error("Ничего не найдено");
        }
    }
}
