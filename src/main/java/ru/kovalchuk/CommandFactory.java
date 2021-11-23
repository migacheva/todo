package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.command.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CommandFactory {

    TaskDao taskDao;
    Printer printer;

    public CommandFactory(TaskDao taskDao, Printer printer) {
        this.taskDao = taskDao;
        this.printer = printer;
    }

    Command getCommand(BufferedReader in) throws ArrayIndexOutOfBoundsException, IOException{
        String fullLine = in.readLine();
        fullLine = fullLine.trim();
        String commandName = Helper.getCommand(fullLine);
        List<String> commandVariables;
        commandVariables = Helper.getData(fullLine);
        if (commandName.equals("add")) {
            return new AddCommand(taskDao, commandVariables.get(0));
        } else if (commandName.equals("print")) {
            return new PrintCommand(taskDao, printer, false);
        } else if (commandName.equals("print all")) {
            return new PrintCommand(taskDao, printer, true);
        } else if (commandName.equals("toggle")) {
            return new ToggleCommand(taskDao, Integer.parseInt(commandVariables.get(0)));
        } else if (commandName.equals("search")) {
            return new SearchCommand(taskDao, printer, commandVariables.get(0));
        } else if (commandName.equals("edit")) {
            return new EditCommand(taskDao, Integer.parseInt(commandVariables.get(0)), commandVariables.get(1));
        } else if (commandName.equals("delete")) {
            return new DeleteCommand(taskDao, Integer.parseInt((commandVariables.get(0))));
         } else if (commandName.equals("quit")) {
             System.exit(0);
        } else {
            Helper.helper();
        }
        return null;
    }
}
