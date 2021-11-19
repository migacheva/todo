package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.command.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class CommandFactory {

    TaskDao taskDao;

    public CommandFactory(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    Command getCommand() throws ArrayIndexOutOfBoundsException, IOException{
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String fullLine = in.readLine();
            fullLine = fullLine.trim();
            String commandName = Helper.getCommand(fullLine);
            List<String> commandVariables;
            commandVariables = Helper.getData(fullLine);
            if (commandName.equals("add")) {
                return new AddCommand(taskDao, commandVariables.get(0));
            } else if (commandName.equals("print")) {
//                Helper.printTasks(taskList, false);
            } else if (commandName.equals("print all")) {
//                Helper.printTasks(taskList, true);
            } else if (commandName.equals("toggle")) {
                return new ToggleCommand(taskDao, Integer.parseInt(commandVariables.get(0)));
            } else if (commandName.equals("search")) {
                return new SearchCommand(taskDao, commandVariables.get(0));
            } else if (commandName.equals("edit")) {
                return new EditCommand(taskDao, Integer.parseInt(commandVariables.get(0)), commandVariables.get(1));
            } else if (commandName.equals("delete")) {
                return new DeleteCommand(taskDao, Integer.parseInt((commandVariables.get(0))));
            // } else if (commandName.equals("quit")) {
            //     break;
            } else {
                log.error("Такой команды нет");
                Helper.helper();
            }
        }
        return null;
    }
}
