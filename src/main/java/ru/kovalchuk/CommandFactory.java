package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.command.*;
import java.io.IOException;

@Slf4j
public class CommandFactory {

    private final TaskDao taskDao;
    private final Printer printer;

    public CommandFactory(TaskDao taskDao, Printer printer) {
        this.taskDao = taskDao;
        this.printer = printer;
    }

    public Command getCommand(String commandName) throws ArrayIndexOutOfBoundsException, IOException{

        if (commandName.equals("add")) {
            return new AddCommand(taskDao);
        } else if (commandName.startsWith("print")) {
            return new PrintCommand(taskDao, printer);
        } else if (commandName.equals("toggle")) {
            return new ToggleCommand(taskDao);
        } else if (commandName.equals("search")) {
            return new SearchCommand(taskDao, printer);
        } else if (commandName.equals("edit")) {
            return new EditCommand(taskDao);
        } else if (commandName.equals("delete")) {
            return new DeleteCommand(taskDao);
         } else if (commandName.equals("quit")) {
             System.exit(0);
        } else {
            Helper.helper();
        }
        return null;
    }
}
